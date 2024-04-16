package com.pedrok.blogservice.contract.user;

import com.pedrok.blogservice.domain.user.model.UserInput;
import com.pedrok.blogservice.domain.user.model.UserOutput;
import com.pedrok.blogservice.domain.user.port.UserApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserApiPort userApiPort;

    @GetMapping
    public Flux<UserOutput> get() {
        return userApiPort.getUsers();
    }

    @PostMapping
    public Mono<UserOutput> create(@RequestBody UserInput input) {
        return userApiPort.create(input);
    }
}
