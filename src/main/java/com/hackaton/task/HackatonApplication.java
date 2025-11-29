package com.hackaton.task;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class HackatonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HackatonApplication.class, args);
    }

}
