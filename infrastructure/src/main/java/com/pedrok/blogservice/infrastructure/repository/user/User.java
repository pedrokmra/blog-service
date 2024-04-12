package com.pedrok.blogservice.infrastructure.repository.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public record User(
        @Id
        String id,
        String name
) {
        public static User from(String name) {
                return new User(null, name);
        }
}
