package ru.dvoretckii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan("ru.dvoretckii")
@EntityScan("ru.dvoretckii")
@ComponentScan(basePackageClasses = RegistrationController.class)
@ComponentScan(basePackageClasses = LoginController.class)
@EnableJpaRepositories("ru.dvoretckii.Repositories")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}