package com.pedrok.blogservice.domain.post.model;

import com.pedrok.blogservice.domain.comment.model.Comment;

import java.time.LocalDateTime;
import java.util.List;

public record Post(
        String id,
        String title,
        String content,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt,
        String userId,
        List<Comment> comments
) {
    public static Post from(
            String id,
            String title,
            String content,
            LocalDateTime publishedAt,
            LocalDateTime updatedAt,
            String userId,
            List<Comment> comments
    ) {
        return new Post(id, title, content, publishedAt, updatedAt, userId, comments);
    }
}
