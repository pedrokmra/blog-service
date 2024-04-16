package com.pedrok.blogservice.domain.post.model;

import com.pedrok.blogservice.domain.comment.model.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record PostOutput(
        String id,
        String title,
        String content,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt,
        String userId,
        List<Comment> comments
) {
    public static PostOutput from(
            String id,
            String title,
            String content,
            LocalDateTime publishedAt,
            LocalDateTime updatedAt,
            String userId
    ) {
        return new PostOutput(id, title, content, publishedAt, updatedAt, userId, new ArrayList<>());
    }

    public static PostOutput from(
            String id,
            String title,
            String content,
            LocalDateTime publishedAt,
            LocalDateTime updatedAt,
            String userId,
            List<Comment> comments
    ) {
        return new PostOutput(id, title, content, publishedAt, updatedAt, userId, comments);
    }
}
