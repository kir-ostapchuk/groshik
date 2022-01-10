package com.ostapchuk.app.groshik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GroshikApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroshikApplication.class, args);
    }
}
