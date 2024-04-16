package com.pedrok.blogservice.domain.post.port;

import com.pedrok.blogservice.domain.post.model.Post;
import com.pedrok.blogservice.domain.post.model.PostInput;
import com.pedrok.blogservice.domain.post.model.PostOutput;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PostSpiPort {
    Flux<PostOutput> getPostsByUserId(String userId);
    Mono<PostOutput> getPostById(String postId);
    Mono<PostOutput> create(PostInput input);
    Mono<PostOutput> update(Post input);
}
