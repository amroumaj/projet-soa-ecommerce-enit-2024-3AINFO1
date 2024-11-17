package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // Désactive explicitement la protection CSRF
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/products/**").permitAll() // Permet l'accès aux API produits sans authentification
                .anyRequest().authenticated()); // Authentifie les autres demandes

        return http.build();
    }
}
