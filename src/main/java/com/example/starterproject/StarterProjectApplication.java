package com.example.starterproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class StarterProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarterProjectApplication.class, args);
    }

}
