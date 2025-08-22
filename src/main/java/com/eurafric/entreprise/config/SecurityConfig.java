package com.eurafric.entreprise.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    /**
     * Bean pour encoder les mots de passe avec BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean AuthenticationManager (utile si tu veux l'injecter pour custom login)
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configuration de la sécurité HTTP avec Spring Security 6
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactiver CSRF pour API REST
                .csrf(csrf -> csrf.disable())

                // Autorisations selon les rôles
                .authorizeHttpRequests(auth -> auth
                        // Autoriser login, swagger, h2-console sans authentification
                        .requestMatchers(
                                "/api/auth/**",
                                "/h2-console/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Endpoints réservés ADMIN
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")

                        // Endpoints accessibles à USER et ADMIN
                        .requestMatchers("/api/users/**").hasAnyRole("USER", "ADMIN")

                        // Toute autre requête nécessite authentification
                        .anyRequest().authenticated()
                )

                // HTTP Basic si nécessaire pour test rapide
                .httpBasic(Customizer.withDefaults());

        // Permettre l'affichage H2 console dans un frame
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
