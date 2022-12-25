package com.example.jdbctemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class JdbctemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbctemplateApplication.class, args);
    }
}
