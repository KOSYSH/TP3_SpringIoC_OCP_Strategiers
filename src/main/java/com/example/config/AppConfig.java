package com.example.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(basePackages = {"com.example.dao", "com.example.metier"})
@PropertySource("classpath:app.properties")
public class AppConfig {
}
