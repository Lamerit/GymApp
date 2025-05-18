package com.keygen.gymapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.keygen.gymapi")
public class GymapiApplication {
    public static void main(String[] args) {
        SpringApplication.run(GymapiApplication.class, args);
    }
}
