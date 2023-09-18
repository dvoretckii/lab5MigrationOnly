package ru.dvoretckii;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.dvoretckii.myHandlers.CustomAuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private CustomAuthenticationFailureHandler failureHandler;

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
                .authorizeHttpRequests().requestMatchers("/login/logged").permitAll()
                .and()
                .authorizeHttpRequests().requestMatchers("/login/error-info").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler((request, response, authentication) -> {
                    response.sendRedirect("/login/logged");
                })
                .failureHandler((request, response, exception) -> {
                    response.sendRedirect("/login/error-info");
                })
                .permitAll()
                .and()
                .httpBasic()
                .and()
                .build();
    }
}