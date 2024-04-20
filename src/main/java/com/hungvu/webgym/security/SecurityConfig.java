package com.hungvu.webgym.security;

import com.hungvu.webgym.security.jwt.JwtAuthFilter;
import com.hungvu.webgym.service.WebUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final WebUserDetailsService webUserDetailsService;
    private final JwtAuthFilter jwtAuthFilter;
    private final CustomLogout customLogout;

    public SecurityConfig(WebUserDetailsService webUserDetailsService, JwtAuthFilter jwtAuthFilter, CustomLogout customLogout) {
        this.webUserDetailsService = webUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.customLogout = customLogout;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req->req.requestMatchers("/api/auth/**",
                                "/api/user/**",
                                "/api/category/**",
                                "/api/product/**",
                                "/api/cart/**",
                                "/api/order/**",
                                "/api/pet-service/**",
                                "/api/booking/**",
                                "api/address/**")
                        .permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .anyRequest()
                        .authenticated()
                ).userDetailsService(webUserDetailsService)
                .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        e-> e.accessDeniedHandler(
                            (request, response, accessDeniedException)->response.setStatus(403)
                        )
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .logout(l-> l
                    .logoutUrl("/logout")
                    .addLogoutHandler(customLogout)
                    .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()
                    ))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
