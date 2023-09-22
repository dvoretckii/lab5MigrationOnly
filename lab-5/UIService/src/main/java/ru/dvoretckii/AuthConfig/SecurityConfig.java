package ru.dvoretckii.AuthConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf()
                .disable()
                .authorizeHttpRequests().requestMatchers("/registration").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/cat/hello").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/cat/get?id=1").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/login/logged");
                })
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/cat/hello");
                })
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .build();
    }
}