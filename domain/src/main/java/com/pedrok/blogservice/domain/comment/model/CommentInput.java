package com.pedrok.blogservice.domain.comment.model;

public record CommentInput(
        String content,
        String userId,
        String postId
) {
}
