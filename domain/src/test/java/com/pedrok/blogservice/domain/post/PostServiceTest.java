package com.pedrok.blogservice.domain.post;

import com.pedrok.blogservice.domain.comment.model.Comment;
import com.pedrok.blogservice.domain.comment.model.CommentInput;
import com.pedrok.blogservice.domain.exception.NotFoundException;
import com.pedrok.blogservice.domain.post.model.Post;
import com.pedrok.blogservice.domain.post.model.PostInput;
import com.pedrok.blogservice.domain.post.model.PostOutput;
import com.pedrok.blogservice.domain.post.model.PostUpdateInput;
import com.pedrok.blogservice.domain.post.port.PostSpiPort;
import com.pedrok.blogservice.domain.user.model.UserOutput;
import com.pedrok.blogservice.domain.user.port.UserSpiPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {
    @Mock
    private PostSpiPort postSpiPort;
    @Mock
    private UserSpiPort userSpiPort;
    @InjectMocks
    private PostService postService;

    @Test
    void itShouldGetPostsByUserId() {
        // GIVEN
        String userId = "123";
        PostOutput postOutput = PostOutput.from("123",
                "title",
                "content",
                LocalDateTime.now(),
                null,
                userId);

        // WHEN
        when(userSpiPort.getUserById(userId)).thenReturn(Mono.just(UserOutput.from(userId, "pedro")));
        when(postSpiPort.getPostsByUserId(userId)).thenReturn(Flux.just(postOutput));

        // THEN
        StepVerifier.create(postService.getPostsByUserId(userId))
                .expectNext(postOutput)
                .verifyComplete();
    }

    @Test
    void itShouldThrowWhenGetPostsByUserIdNotFoundUserId() {
        // GIVEN
        String userId = "123";

        // WHEN
        when(userSpiPort.getUserById(userId)).thenReturn(Mono.empty());

        // THEN
        StepVerifier.create(postService.getPostsByUserId(userId))
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
                        throwable.getMessage().equals("userId " + userId + " not found"))
                .verify();
    }

    @Test
    void itShouldThrowWhenGetPostsByUserIdNotFoundPostId() {
        // GIVEN
        String userId = "123";
        PostOutput postOutput = PostOutput.from("123",
                "title",
                "content",
                LocalDateTime.now(),
                null,
                userId);

        // WHEN
        when(userSpiPort.getUserById(userId)).thenReturn(Mono.just(UserOutput.from(userId, "pedro")));
        when(postSpiPort.getPostsByUserId(userId)).thenReturn(Flux.empty());

        // THEN
        StepVerifier.create(postService.getPostsByUserId(userId))
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
                        throwable.getMessage().equals("posts not found for userId " + userId))
                .verify();
    }

    @Test
    void itShouldCreate() {
        // GIVEN
        String userId = "123";

        PostInput postInput = new PostInput("title", "content", userId);

        PostOutput postOutput = PostOutput.from("123",
                "title",
                "content",
                LocalDateTime.now(),
                null,
                userId);

        // WHEN
        when(userSpiPort.getUserById(userId)).thenReturn(Mono.just(UserOutput.from(userId, "pedro")));
        when(postSpiPort.create(postInput)).thenReturn(Mono.just(postOutput));

        // THEN
        StepVerifier.create(postService.create(postInput))
                .expectNext(postOutput)
                .verifyComplete();
    }

    @Test
    void itShouldThrowWhenCreateNotFoundUser() {
        // GIVEN
        String userId = "123";

        PostInput postInput = new PostInput("title", "content", userId);

        // WHEN
        when(userSpiPort.getUserById(userId)).thenReturn(Mono.empty());

        // THEN
        StepVerifier.create(postService.create(postInput))
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException &&
                        throwable.getMessage().equals("userId " + userId + " not found"))
                .verify();
    }

    @Test
    void itShouldUpdate() {
        // GIVEN
        String updatedContent = "updated content";
        PostUpdateInput postUpdateInput = new PostUpdateInput("123", updatedContent);

        PostOutput postOutput = PostOutput.from("123",
                "title",
                "content",
                LocalDateTime.now(),
                null,
                "123");

        PostOutput postUpdatedOutput = PostOutput.from("123",
                "title",
                updatedContent,
                LocalDateTime.now(),
                LocalDateTime.now(),
                "123");

        // WHEN
        when(postSpiPort.getPostById("123")).thenReturn(Mono.just(postOutput));
        when(postSpiPort.update(any(Post.class))).thenReturn(Mono.just(postUpdatedOutput));

        // THEN
        StepVerifier.create(postService.update(postUpdateInput))
                .expectNextMatches(updatedPost -> updatedPost.content().equals(updatedContent))
                .verifyComplete();
    }

    @Test
    void itShouldThrowUpdateWhenPostNotFound() {
        // GIVEN
        String updatedContent = "updated content";
        PostUpdateInput postUpdateInput = new PostUpdateInput("123", updatedContent);

        // WHEN
        when(postSpiPort.getPostById("123")).thenReturn(Mono.empty());

        // THEN
        StepVerifier.create(postService.update(postUpdateInput))
                .expectError(NotFoundException.class)
                .verify();
    }

    @Test
    void itShouldCreateComment() {
        // GIVEN
        String userId = "123";
        String postId = "123";

        CommentInput commentInput = new CommentInput("content", userId, postId);

        PostOutput postOutput = PostOutput.from("123",
                "title",
                "content",
                LocalDateTime.now(),
                null,
                userId);

        // WHEN
        when(userSpiPort.getUserById(userId)).thenReturn(Mono.just(UserOutput.from(userId, "pedro")));

        when(postSpiPort.getPostById(postId)).thenReturn(Mono.just(postOutput));

        postOutput.comments().add(Comment.from("content", userId, LocalDateTime.now()));
        when(postSpiPort.update(any(Post.class))).thenReturn(Mono.just(postOutput));

        // THEN
        StepVerifier.create(postService.createComment(commentInput))
                .expectNextMatches(comment -> comment.content().equals("content"))
                .verifyComplete();
    }
}
