package com.pedrok.blogservice.contract.post;

import com.pedrok.blogservice.domain.post.model.PostInput;
import com.pedrok.blogservice.domain.post.model.PostOutput;
import com.pedrok.blogservice.domain.post.model.PostUpdateInput;
import com.pedrok.blogservice.domain.post.port.PostApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostApiPort postApiPort;

    @GetMapping("{userId}")
    public Flux<PostOutput> get(@PathVariable("userId") String userId) {
        return postApiPort.getPostsByUserId(userId);
    }

    @PostMapping
    public Mono<PostOutput> create(@RequestBody PostInput input) {
        return postApiPort.create(input);
    }

    @PatchMapping
    public Mono<PostOutput> update(@RequestBody PostUpdateInput input) {
        return postApiPort.update(input);
    }
}
