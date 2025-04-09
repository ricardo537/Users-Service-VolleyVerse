package com.volleyverse.users_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	@Bean
	PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers("/volleyverse/api/auth/register", 
                                		"/volleyverse/api/auth/login", 
                                		"/volleyverse-doc/swagger-ui.html", // Ensure Swagger UI entry point is allowed
                                        "/volleyverse-doc/swagger-ui/**", // Allow access to Swagger UI resources
                                        "/v3/api-docs/**", // Swagger docs access
                                        "/swagger-ui/**" // Swagger UI endpoint
                                		).permitAll()
                                .anyRequest().permitAll()
                );
        return http.build();
    }

}
