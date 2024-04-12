package com.pedrok.blogservice;

import com.pedrok.blogservice.domain.user.model.UserInput;
import com.pedrok.blogservice.domain.user.port.UserSpiPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class BlogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(UserSpiPort spiPort) {
        return args -> spiPort.getUserById("66196019e8e6121b34983d2e")
                .flatMap(userOutput -> {
                    System.out.printf(userOutput.toString());
                    return Mono.empty();
                })
                .then(spiPort.create(new UserInput("Felipe")))
                .thenMany(spiPort.getUsers())
                .doOnNext(System.out::print)
                .subscribe();
    }
}
