package ru.dvoretckii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.dvoretckii.responses.ServiceOwner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@RestController
public class RegistrationController {

    @Autowired
    private BusinessOwner businessOwner;

    @GetMapping(value = "/registration")
    public RedirectView registration(@RequestParam(required = false, defaultValue = "1990-12-12") String birthdate,
                                     @RequestParam String name,
                                     @RequestParam String password) {

        ServiceOwner owner = new ServiceOwner();
        owner.setName(name);
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(birthdate);
            owner.setOwner_birth_date(date);
            owner.setPassword(password);
            businessOwner.saveUser(owner);
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/login");
            return redirectView;
        } catch (ParseException e) {
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/reg/err");
            return redirectView;
        }
    }
}