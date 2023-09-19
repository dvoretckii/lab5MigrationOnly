package ru.dvoretckii.Controllers.OwnerControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/user")
public class UserController implements OwnerControllerInterface {
//    @Autowired
//    BusinessCat businessCat;
//    @Autowired
//    BusinessOwner businessOwner;
//
//    @GetMapping(value = "/create-cat/{name}/{breed}/{birthDate}/{color}/{ownerId}", produces = APPLICATION_JSON_VALUE)
//    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
//    public RedirectView createCat(@PathVariable String name, @PathVariable String breed, @PathVariable String birthDate, @PathVariable String color, @PathVariable long ownerId) {
//        ServiceOwner serviceOwner = (ServiceOwner) businessOwner.loadUserByUsername(username);
//        if (serviceOwner.getOwner_id() != ownerId) {
//            message = "Error. U Can't create cat for another person";
//            RedirectView redirectView = new RedirectView();
//            redirectView.setUrl("/err/err-info");
//            return redirectView;
//        }
//        ServiceCat cat = new ServiceCat();
//        cat.setServiceCat_name(name);
//        cat.setServiceCat_breed(breed);
//        cat.setColor(Color.valueOf(color.toUpperCase()));
//        try {
//            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
//            cat.setServiceCat_birth_date(date);
//            businessCat.createCat(cat);
//            ServiceOwner serviceOwner1 = businessOwner.getOwnerById(ownerId);
//            businessCat.changeCatOwner(cat, serviceOwner1);
//            message = "Cat " + cat.getServiceCat_name() + " was successfully created for owner " + serviceOwner1.getName();
//            RedirectView redirectView = new RedirectView();
//            redirectView.setUrl("/suc/suc-info");
//            return redirectView;
//        } catch (ParseException e) {
//            message = "Error. Wrong date format. Follow dd-MM-yyyy";
//            RedirectView redirectView = new RedirectView();
//            redirectView.setUrl("/err/err-info");
//            return redirectView;
//        }
//    }
//
//    @Override
//    @GetMapping(value = "/delete-cat/{catId}", produces = APPLICATION_JSON_VALUE)
//    public RedirectView deleteCat(@PathVariable long catId) {
//        if (businessCat.getCatById(catId) == null) {
//            message = "No such cat";
//            RedirectView redirectView = new RedirectView();
//            redirectView.setUrl("/err/err-info");
//            return redirectView;
//        }
//        Set<ServiceCat> serviceCats = businessOwner.getOwnedCats(username);
//        for (ServiceCat cat1 : serviceCats) {
//            if (cat1.getServiceCat_id() == catId) {
//                ServiceCat serviceCat = businessCat.getCatById(catId);
//                message = "Your cat: \n name - " + serviceCat.getServiceCat_name() + " was deleted";
//                businessCat.deleteCat(serviceCat);
//                RedirectView redirectView = new RedirectView();
//                redirectView.setUrl("/suc/suc-info");
//                return redirectView;
//            }
//        }
//        message = "You don't have permission for this cat";
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/err/err-info");
//        return redirectView;
//    }
//
//    @Override
//    @GetMapping(value = "/start", produces = APPLICATION_JSON_VALUE)
//    public String start() {
//        return "Hi, " + username +
//                "\nYour cats: \n"
//                + businessOwner.getOwnedCats(username)
//                + " \n list of commands: \n /start \n /create-cat \n /delete-cat \n /cat-info";
//    }
//
//    @Override
//    @GetMapping(value = "/get/{catId}")
//    public RedirectView getCat(@PathVariable long catId) {
//        if (businessCat.getCatById(catId) == null) {
//            message = "No such cat";
//            RedirectView redirectView = new RedirectView();
//            redirectView.setUrl("/err/err-info");
//            return redirectView;
//        }
//        Set<ServiceCat> serviceCats = businessOwner.getOwnedCats(username);
//        for (ServiceCat cat1 : serviceCats) {
//            if (cat1.getServiceCat_id() == catId) {
//                ServiceCat serviceCat = businessCat.getCatById(catId);
//                message = "Your cat: \n name - " + serviceCat.getServiceCat_name() +
//                        "\n breed - " + serviceCat.getServiceCat_breed() +
//                        "\n Color - " + serviceCat.getColor() +
//                        "\n birthdate - " + serviceCat.getServiceCat_birth_date() +
//                        "\n his friends : \n" + businessCat.getFriends(serviceCat);
//                RedirectView redirectView = new RedirectView();
//                redirectView.setUrl("/suc/suc-info");
//                return redirectView;
//            }
//        }
//        message = "You don't have permission for this cat";
//        RedirectView redirectView = new RedirectView();
//        redirectView.setUrl("/err/err-info");
//        return redirectView;
//    }
}
