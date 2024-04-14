package com.pedrok.blogservice.domain.post.port;

import com.pedrok.blogservice.domain.comment.model.Comment;
import com.pedrok.blogservice.domain.comment.model.CommentInput;
import com.pedrok.blogservice.domain.post.model.PostInput;
import com.pedrok.blogservice.domain.post.model.PostOutput;
import com.pedrok.blogservice.domain.post.model.PostUpdateInput;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostApiPort {
    Flux<PostOutput> getPostsByUserId(String userId);
    Mono<PostOutput> create(PostInput input);
    Mono<PostOutput> update(PostUpdateInput input);
    Mono<Comment> createComment(CommentInput commentInput);
}
