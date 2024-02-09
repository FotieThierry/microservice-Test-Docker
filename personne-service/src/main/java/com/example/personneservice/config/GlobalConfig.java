package com.example.personneservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.global")
public record GlobalConfig(int a, int b) {
}
