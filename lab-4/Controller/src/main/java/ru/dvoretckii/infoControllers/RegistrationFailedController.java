package ru.dvoretckii.infoControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/reg")
public class RegistrationFailedController {
    @GetMapping(value = "/err")
    public String loginErrorInfo(){
        return "Registration failed";
    }
}