package com.pedrok.blogservice.domain.user.model;

public record UserOutput(
        String id,
        String name
) {
    public static UserOutput from(String id, String name) {
        return new UserOutput(id, name);
    }
}
