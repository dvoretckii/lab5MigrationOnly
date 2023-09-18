package ru.dvoretckii.infoControllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static ru.dvoretckii.LoginController.message;
@RestController
@RequestMapping("/err")
public class ErrorController {
    @GetMapping(value = "/err-info")
    public String loginErrorInfo(){
        return message;
    }
}