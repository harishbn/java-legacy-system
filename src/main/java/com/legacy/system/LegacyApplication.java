package com.legacy.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// LEGACY: No proper JavaDoc or description
@SpringBootApplication
public class LegacyApplication {
    // LEGACY: Public static void main in a Spring Boot app class (should be in separate runner)
    public static void main(String[] args) {
        // LEGACY: Direct static access to SpringApplication
        SpringApplication.run(LegacyApplication.class, args);
    }
} 