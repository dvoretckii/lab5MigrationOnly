package ru.dvoretckii;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.dvoretckii.Controllers.AuthControllers.LoginController;
import ru.dvoretckii.Controllers.AuthControllers.RegistrationController;
import ru.dvoretckii.Controllers.CatControllers.CatController;
import ru.dvoretckii.Controllers.InfoControllers.ErrorController;
import ru.dvoretckii.Controllers.InfoControllers.RegistrationFailedController;
import ru.dvoretckii.Controllers.InfoControllers.SuccessController;
import ru.dvoretckii.Controllers.OwnerControllers.OwnerController;

//@SpringBootApplication
////@EnableJpaRepositories("ru.dvoretckii.DAO")
////@ComponentScan(basePackageClasses = RegistrationController.class)
////@ComponentScan(basePackageClasses = LoginController.class)
//public class Application {
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }
//}


@Configuration
@EnableAutoConfiguration
@ComponentScan("ru.dvoretckii")
@EntityScan("ru.dvoretckii")
@ComponentScan(basePackageClasses = OwnerController.class)
@ComponentScan(basePackageClasses = RegistrationController.class)
@ComponentScan(basePackageClasses = CatController.class)
@ComponentScan(basePackageClasses = ErrorController.class)
@ComponentScan(basePackageClasses = RegistrationFailedController.class)
@ComponentScan(basePackageClasses = SuccessController.class)
@EnableJpaRepositories("ru.dvoretckii.DAO")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
