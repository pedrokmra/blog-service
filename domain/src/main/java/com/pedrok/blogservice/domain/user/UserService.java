package com.pedrok.blogservice.domain.user;

import com.pedrok.blogservice.domain.exception.BadRequestException;
import com.pedrok.blogservice.domain.user.model.UserInput;
import com.pedrok.blogservice.domain.user.model.UserOutput;
import com.pedrok.blogservice.domain.user.port.UserApiPort;
import com.pedrok.blogservice.domain.user.port.UserSpiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService implements UserApiPort {
    private final UserSpiPort userSpiPort;

    @Override
    public Flux<UserOutput> getUsers() {
        return userSpiPort.getUsers();
    }

    @Override
    public Mono<UserOutput> create(UserInput input) {
        return userSpiPort.getUserByName(input.name())
                .flatMap(user -> Mono.error(new BadRequestException("name already registered")))
                .switchIfEmpty(userSpiPort.create(input)).cast(UserOutput.class);
    }
}
