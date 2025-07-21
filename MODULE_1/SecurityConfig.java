package com.routemax.teamroutemax.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(auth -> auth
            //.requestMatchers("/**").permitAll() // <-- allow all for now
            //.requestMatchers("/", "/register", "/admin").permitAll()
             .requestMatchers("/", "/register", "/admin", "/admin/users/**").permitAll()
             //.anyRequest().authenticated()
            .anyRequest().permitAll()
        );
    return http.build();
}
// Example: SecurityConfig.java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
        .and()
        .formLogin();
    // CSRF is enabled by default, so include the token in your forms!
}
}

