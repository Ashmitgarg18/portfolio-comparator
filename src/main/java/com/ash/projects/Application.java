package com.ash.projects;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.ash.projects")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}