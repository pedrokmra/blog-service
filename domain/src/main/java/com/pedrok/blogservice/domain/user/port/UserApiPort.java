package com.pedrok.blogservice.domain.user.port;

import com.pedrok.blogservice.domain.user.model.UserInput;
import com.pedrok.blogservice.domain.user.model.UserOutput;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserApiPort {
    Flux<UserOutput> getUsers();
    Mono<UserOutput> create(UserInput input);
}
