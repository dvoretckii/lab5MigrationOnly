package ru.dvoretckii.Controllers.InfoControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/err")
public class ErrorController {
    @GetMapping(value = "/err-info")
    public String loginErrorInfo(){
        return "Error";
    }
}
