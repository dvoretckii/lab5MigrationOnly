package ru.dvoretckii;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.dvoretckii.responses.ServiceCat;
import ru.dvoretckii.responses.ServiceOwner;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    BusinessCat businessCat;
    @Autowired
    BusinessOwner businessOwner;
    String username = null;

    @Autowired
    public LoginController(BusinessCat businessCat) {
        this.businessCat = businessCat;
    }

    @GetMapping("/logged/")
    public String login(Authentication authentication) {
        username = authentication.getName();
        return "Привет, " +  username +
                "!\nХорошая сегодня погода\nЖелаю счастья в личной жизни, а пока глянь на своих котиков\n"
                + businessOwner.getOwnedCats(username);
    }

    @GetMapping(value = "/logged/cat_info")
    public String getCat(@RequestParam(required = true) String catId) {
        long catid = Long.valueOf(catId);
        if (businessCat.getCatById(catid) == null)
        {
            return "No such cat";
        }
        ServiceCat cat = businessCat.getCatById(catid);
        if (cat.getServiceOwner().getName() == username) {
            return cat.toString();
        }
       else {
           return "Это не твой котик";
        }
    }
}