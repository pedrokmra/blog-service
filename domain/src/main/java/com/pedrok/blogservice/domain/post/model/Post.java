package com.pedrok.blogservice.domain.post.model;

import java.time.LocalDateTime;

public record Post(
        String id,
        String title,
        String content,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt,
        String userId
) {
    public static Post from(
            String id,
            String title,
            String content,
            LocalDateTime publishedAt,
            LocalDateTime updatedAt,
            String userId
    ) {
        return new Post(id, title, content, publishedAt, updatedAt, userId);
    }
}
