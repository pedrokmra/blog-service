package com.pedrok.blogservice.infrastructure.repository.post;

import com.pedrok.blogservice.domain.comment.model.Comment;
import com.pedrok.blogservice.domain.post.model.PostInput;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Document("posts")
public record Post(
        @Id
        String id,
        String title,
        String content,
        LocalDateTime publishedAt,
        LocalDateTime updatedAt,
        String userId,
        List<Comment> comments
) {
        public static Post from(PostInput input, LocalDateTime publishedAt) {
                return new Post(null, input.title(), input.content(), publishedAt, null, input.userId(), new ArrayList<>());
        }

        public static Post from(com.pedrok.blogservice.domain.post.model.Post input) {
                return new Post(input.id(), input.title(), input.content(), input.publishedAt(), input.updatedAt(), input.userId(), input.comments());
        }
}
