package com.example.registreservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistreServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistreServiceApplication.class, args);
    }

}
