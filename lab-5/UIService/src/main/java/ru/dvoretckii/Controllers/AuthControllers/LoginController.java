package ru.dvoretckii.Controllers.AuthControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import ru.dvoretckii.DAO.Entities.Role;
import ru.dvoretckii.Service.AuthService;
import ru.dvoretckii.Service.ServiceOwner;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    AuthService authService;
    public static String username = null;

    @Autowired
    public LoginController(AuthService businessCat) {
        this.authService = businessCat;
    }

    @GetMapping(value = "/error-info")
    public String loginErrorInfo(){
        return authService.findByUsername(username);
   }
}
