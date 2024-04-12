package com.pedrok.blogservice.infrastructure.adapter;

import com.pedrok.blogservice.domain.user.model.UserInput;
import com.pedrok.blogservice.domain.user.model.UserOutput;
import com.pedrok.blogservice.domain.user.port.UserSpiPort;
import com.pedrok.blogservice.infrastructure.repository.user.User;
import com.pedrok.blogservice.infrastructure.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class UserSpiImpl implements UserSpiPort {
    private final UserRepository repository;

    @Override
    public Flux<UserOutput> getUsers() {
        return repository.findAll().map(user -> UserOutput.from(user.id(), user.name()));
    }

    @Override
    public Mono<UserOutput> getUserById(String id) {
        return repository.findById(id).map(user -> UserOutput.from(user.id(), user.name()));
    }

    @Override
    public Mono<UserOutput> getUserByName(String name) {
        return repository.findByName(name).map(user -> UserOutput.from(user.id(), user.name()));
    }

    @Override
    public Mono<UserOutput> create(UserInput input) {
        return repository.save(User.from(input.name())).map(user -> UserOutput.from(user.id(), user.name()));
    }
}
