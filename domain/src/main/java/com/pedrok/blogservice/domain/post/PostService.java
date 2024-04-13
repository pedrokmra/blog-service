package com.pedrok.blogservice.domain.post;

import com.pedrok.blogservice.domain.post.model.Post;
import com.pedrok.blogservice.domain.post.model.PostInput;
import com.pedrok.blogservice.domain.post.model.PostOutput;
import com.pedrok.blogservice.domain.post.model.PostUpdateInput;
import com.pedrok.blogservice.domain.post.port.PostApiPort;
import com.pedrok.blogservice.domain.post.port.PostSpiPort;
import com.pedrok.blogservice.domain.user.port.UserSpiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService implements PostApiPort {
    private final PostSpiPort postSpiPort;
    private final UserSpiPort userSpiPort;

    @Override
    public Flux<PostOutput> getPostsByUserId(String userId) {
        return userSpiPort.getUserById(userId)
                .switchIfEmpty(Mono.error(new RuntimeException("userId " + userId + " not found")))
                .flatMapMany(user -> postSpiPort.getPostsByUserId(user.id()))
                .switchIfEmpty(Mono.error(new RuntimeException("posts not found for userId " + userId)));
    }

    @Override
    public Mono<PostOutput> create(PostInput input) {
        return userSpiPort.getUserById(input.userId())
                .switchIfEmpty(Mono.error(new RuntimeException("userId " + input.userId() + " not found")))
                .then(postSpiPort.create(input));
    }

    @Override
    public Mono<PostOutput> update(PostUpdateInput input) {
        return postSpiPort.getPostById(input.id())
                .switchIfEmpty(Mono.error(new RuntimeException("postId " + input.id() + " not found")))
                .flatMap(post -> postSpiPort.update(
                        Post.from(
                                post.id(),
                                post.title(),
                                input.content(),
                                post.publishedAt(),
                                LocalDateTime.now(),
                                post.userId()
                        )
                ));
    }
}