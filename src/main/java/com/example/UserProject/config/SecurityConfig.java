package com.example.UserProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // registration allowed for everyone
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()

                        // only ADMIN can delete
                        .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")

                        // only ADMIN can see all users
                        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")

                        // USER and ADMIN can see single user
                        .requestMatchers(HttpMethod.GET, "/users/**").hasAnyRole("USER","ADMIN")

                        .anyRequest().authenticated()
                )

                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}