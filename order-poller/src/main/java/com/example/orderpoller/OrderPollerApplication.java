package com.example.orderpoller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class OrderPollerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderPollerApplication.class, args);
    }
}
