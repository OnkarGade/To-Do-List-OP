package com.build.todo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a source of bean definitions and configurations
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // Apply CORS configuration globally to all paths (/**)
        registry.addMapping("/**")
                .allowedOrigins("*") // Allows requests from ANY origin (your requirement)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allows standard methods
                .allowedHeaders("*"); // Allows all headers
        // .allowCredentials(true); // If you were sending cookies/authentication, you'd use this (but then allowedOrigins can't be '*')
    }
}