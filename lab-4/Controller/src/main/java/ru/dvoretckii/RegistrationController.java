package ru.dvoretckii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dvoretckii.responses.ServiceOwner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Controller
public class RegistrationController {

    @Autowired
    private BusinessOwner businessOwner;

    @GetMapping(value = "/registration")
    public String registration(@RequestParam(required = false, defaultValue = "1990-12-12") String birthday,
                                                      @RequestParam String name,
                                                      @RequestParam String password) {

        ServiceOwner owner = new ServiceOwner();
        owner.setName(name);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date;
        try {
            date = formatter.parse(birthday);
        } catch (ParseException e) {
            return "Cannot parse birth date. Try to follow this format: yyyy-MM-dd";
        }
        owner.setOwner_birth_date(date);
        owner.setPassword(password);
        businessOwner.saveUser(owner);
        return "redirect:/login";
    }

    @GetMapping(value = "/")
    public ResponseEntity<String> home() {
        return ResponseEntity.ok("Всем привет\n<3");
    }
}
