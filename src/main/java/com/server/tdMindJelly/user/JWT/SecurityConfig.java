package com.server.tdMindJelly.user.JWT;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * @author : KTDS
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-30     KTDS      주석최초생성
 * @className : SecurityConfig
 * @description :
 * @modification : 2024-12-30 (KTDS)
 * @date : 2024-12-30
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] PUBLIC_STATIC_RESOURCES = {
            "/images/**", "/icons/**"
    };

    private static final String[] DEV_ONLY_RESOURCES = {
            "/h2-console/**"
    };

    private static final String[] PUBLIC_GET_ENDPOINTS = {
            "/users/check-email", "/users/check-phone", "/users/check-nickname",
            "/basicEmo", "/basicEmo/**",
            "/jellyComb", "/jellyComb/**"
    };

    private static final String[] PUBLIC_POST_ENDPOINTS = {
            "/users", "/users/login", "/users/find-email", "/users/find-password"
    };

    private final JwtRequestFilter jwtRequestFilter;
    private final Environment environment;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter, Environment environment) {
        this.jwtRequestFilter = jwtRequestFilter;
        this.environment = environment;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorizeRequests -> {
                        authorizeRequests
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll()
                                .requestMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll()
                                .requestMatchers(PUBLIC_STATIC_RESOURCES).permitAll();

                        if (environment.acceptsProfiles(Profiles.of("local"))) {
                            authorizeRequests.requestMatchers(DEV_ONLY_RESOURCES).permitAll();
                        }

                        authorizeRequests.anyRequest().authenticated();
                })
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
}
