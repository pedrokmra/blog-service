package com.pedrok.blogservice.contract.post;

import com.pedrok.blogservice.domain.comment.model.Comment;
import com.pedrok.blogservice.domain.comment.model.CommentInput;
import com.pedrok.blogservice.domain.post.model.PostInput;
import com.pedrok.blogservice.domain.post.model.PostOutput;
import com.pedrok.blogservice.domain.post.model.PostUpdateInput;
import com.pedrok.blogservice.domain.post.port.PostApiPort;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND", content = @Content()),
    })
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

    @PostMapping("comments")
    public Mono<Comment> createComment(@RequestBody CommentInput input) {
        return postApiPort.createComment(input);
    }
}
