package com.pedrok.blogservice;

import com.pedrok.blogservice.domain.user.model.UserInput;
import com.pedrok.blogservice.domain.user.port.UserApiPort;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogServiceApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(UserApiPort userApiPort) {
//        return args -> userApiPort.create(new UserInput("Felipe"))
//                .thenMany(userApiPort.getUsers())
//                .doOnNext(System.out::print)
//                .subscribe();
//    }
}
