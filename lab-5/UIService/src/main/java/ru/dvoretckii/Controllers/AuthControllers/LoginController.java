package ru.dvoretckii.Controllers.AuthControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("/login")
public class LoginController {
//    @Autowired
//    BusinessCat businessCat;
//    @Autowired
//    BusinessOwner businessOwner;
//    public static String username = null;
//    public static String message = "";
//
//    @Autowired
//    public LoginController(BusinessCat businessCat) {
//        this.businessCat = businessCat;
//    }
//
//    @GetMapping("/logged")
//    public RedirectView login(Authentication authentication) {
//        username = authentication.getName();
//        ServiceOwner serviceOwner = (ServiceOwner)businessOwner.loadUserByUsername(username);
//        Set<Role> roles = serviceOwner.getRoles();;
//        for (Role role: roles) {
//            System.out.println("\n\n\n\n" + role.getName() + "\n\n\n\n");
//            if (Objects.equals(role.getName(), "ROLE_ADMIN")){
//                RedirectView redirectView = new RedirectView();
//                redirectView.setUrl("/admin/start");
//                return redirectView;
//            }
//        }
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/user/start");
//        return redirectView;
//    }
//    @GetMapping(value = "/error-info")
//    public String loginErrorInfo(){
//        return businessOwner.findByUsername(username);
//   }
}
