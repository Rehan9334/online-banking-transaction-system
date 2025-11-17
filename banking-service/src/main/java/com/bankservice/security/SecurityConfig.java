package com.bankservice.security;


import com.bankservice.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth

                        // Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/accounts/**").permitAll()           // POST + GET
                        .requestMatchers("/api/transactions/**").permitAll()    // entire txn CRUD public

                        // Swagger
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/swagger-resources/**"
                        ).permitAll()

                        // USER-only endpoints
                        .requestMatchers("/api/accounts/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/transfer").hasAnyRole("USER", "ADMIN")

                        // ADMIN-only endpoints
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Everything else requires JWT
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
