package com.pedrok.blogservice.domain.post.model;

public record PostInput(
        String title,
        String content,
        String userId
) {
}
