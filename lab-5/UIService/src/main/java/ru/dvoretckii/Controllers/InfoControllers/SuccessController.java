package ru.dvoretckii.Controllers.InfoControllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/suc")
public class SuccessController {
    @GetMapping(value = "/suc-info")
    public String loginErrorInfo(){
        return "Success";
    }
}
