package com.gamexo.backend.config.security;

import com.gamexo.backend.config.security.jwt.JwtTokenFilter;
import com.gamexo.backend.config.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(sessionMngConfig -> sessionMngConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET, "/users", "/users/**").authenticated();
                    auth.requestMatchers(HttpMethod.GET, "/products/**").authenticated();
                    auth.requestMatchers("/order", "/order/**").authenticated();
                    auth.requestMatchers("/cart", "/cart/**").authenticated();
                    auth.anyRequest().permitAll();
                })
                .addFilterBefore(new JwtTokenFilter(jwtUtils), BasicAuthenticationFilter.class)
                .build();
    }

}
