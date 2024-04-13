package com.pedrok.blogservice.domain.post.model;

import java.time.LocalDateTime;

public record PostOutput(
        String id,
        String title,
        String content,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt,
        String userId
) {
    public static PostOutput from(
            String id,
            String title,
            String content,
            LocalDateTime publishedAt,
            LocalDateTime updatedAt,
            String userId
    ) {
        return new PostOutput(id, title, content, publishedAt, updatedAt, userId);
    }
}
