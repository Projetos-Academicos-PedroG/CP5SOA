package br.com.fiap3ess.autoescola3ess.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors
                        .configurationSource(request -> {
                            CorsConfiguration configuration = new CorsConfiguration();

                            configuration.setAllowedOriginPatterns(Arrays.asList(
                                    "http://localhost:3000",
                                    "http://127.0.0.1:3000",
                                    "http://localhost:5500",
                                    "http://127.0.0.1:5500"
                            ));

                            configuration.setAllowedMethods(List.of(
                                    "GET",
                                    "POST",
                                    "PUT",
                                    "DELETE",
                                    "OPTIONS",
                                    "HEAD"
                            ));

                            configuration.setAllowedHeaders(List.of(
                                    "Authorization",
                                    "Content-Type",
                                    "Accept",
                                    "Origin"
                            ));

                            configuration.setAllowCredentials(true);

                            return configuration;
                        })
                )

                .sessionManagement(sm ->
                        sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth ->
                        auth
                                // Login
                                .requestMatchers("/login").permitAll()

                                // CEP
                                .requestMatchers("/cep/**").permitAll()

                                // Swagger / OpenAPI
                                .requestMatchers(
                                        "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/v3/api-docs.yaml"
                                ).permitAll()

                                // Usuários
                                .requestMatchers("/usuarios/**").hasRole("ADMIN")

                                // Demais endpoints
                                .anyRequest().authenticated()
                )

                .addFilterBefore(
                        securityFilter,
                        UsernamePasswordAuthenticationFilter.class
                )

                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration
    ) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}