package com.pedrok.blogservice.domain.comment.model;

import java.time.LocalDateTime;

public record Comment(
        String content,
        String userId,
        LocalDateTime publishedAt
) {
    public static Comment from(String content, String userId, LocalDateTime publishedAt) {
        return new Comment(content, userId, publishedAt);
    }
}
