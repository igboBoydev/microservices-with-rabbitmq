package com.abelkelly.notification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NoficationApplication {
    public static void main(String[] args) {
        SpringApplication.run(NoficationApplication.class, args);
    }
}
