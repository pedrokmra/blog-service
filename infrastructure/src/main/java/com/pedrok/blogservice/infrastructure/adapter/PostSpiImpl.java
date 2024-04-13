package com.pedrok.blogservice.infrastructure.adapter;

import com.pedrok.blogservice.domain.post.model.PostInput;
import com.pedrok.blogservice.domain.post.model.PostOutput;
import com.pedrok.blogservice.domain.post.port.PostSpiPort;
import com.pedrok.blogservice.infrastructure.repository.post.Post;
import com.pedrok.blogservice.infrastructure.repository.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PostSpiImpl implements PostSpiPort {
    private final PostRepository repository;

    @Override
    public Flux<PostOutput> getPostsByUserId(String userId) {
        return repository.findAllByUserId(userId).map(post -> PostOutput.from(
                post.id(),
                post.title(),
                post.content(),
                post.publishedAt(),
                post.updatedAt(),
                post.userId()
        ));
    }

    @Override
    public Mono<PostOutput> getPostById(String postId) {
        return repository.findById(postId).map(post -> PostOutput.from(
                post.id(),
                post.title(),
                post.content(),
                post.publishedAt(),
                post.updatedAt(),
                post.userId()
        ));
    }

    @Override
    public Mono<PostOutput> create(PostInput input) {
        return repository.save(Post.from(input, LocalDateTime.now()))
                .map(post -> PostOutput.from(
                        post.id(),
                        post.title(),
                        post.content(),
                        post.publishedAt(),
                        post.updatedAt(),
                        post.userId()
                ));
    }

    @Override
    public Mono<PostOutput> update(com.pedrok.blogservice.domain.post.model.Post input) {
        return repository.save(Post.from(input))
                .map(post -> PostOutput.from(
                        post.id(),
                        post.title(),
                        post.content(),
                        post.publishedAt(),
                        post.updatedAt(),
                        post.userId()
                ));
    }
}
