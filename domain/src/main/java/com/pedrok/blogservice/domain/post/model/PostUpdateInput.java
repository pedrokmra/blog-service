package com.pedrok.blogservice.domain.post.model;

public record PostUpdateInput(
        String id,
        String content
) {
}
