package com.routemax.teamroutemax.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Will allow frontend calls later
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // Update this when frontend is ready
                .allowedMethods("GET", "POST", "PUT", "DELETE");
    }
}
