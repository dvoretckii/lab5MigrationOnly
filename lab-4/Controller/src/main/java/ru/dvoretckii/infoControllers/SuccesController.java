package ru.dvoretckii.infoControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ru.dvoretckii.LoginController.message;

@RestController
@RequestMapping("/suc")
public class SuccesController {
    @GetMapping(value = "/suc-info")
    public String loginErrorInfo(){
        return message;
    }
}
