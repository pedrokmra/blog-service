package com.pedrok.blogservice.infrastructure.repository.post;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {
    Flux<Post> findAllByUserId(String userId);
}
