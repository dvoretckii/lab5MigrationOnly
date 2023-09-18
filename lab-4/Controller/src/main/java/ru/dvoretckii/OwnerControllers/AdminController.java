package ru.dvoretckii.OwnerControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.dvoretckii.BusinessCat;
import ru.dvoretckii.BusinessOwner;
import ru.dvoretckii.Entities.Color;
import ru.dvoretckii.responses.ServiceCat;
import ru.dvoretckii.responses.ServiceOwner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;
import static ru.dvoretckii.LoginController.message;
import static ru.dvoretckii.LoginController.username;

@RestController
@RequestMapping("/admin")
public class AdminController implements  OwnerControllerInterface{
    @Autowired
    BusinessCat businessCat;
    @Autowired
    BusinessOwner businessOwner;

    @GetMapping(value = "/create-cat/{name}/{breed}/{birthDate}/{color}/{ownerId}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.MOVED_PERMANENTLY)
    public RedirectView createCat(@PathVariable String name, @PathVariable String breed, @PathVariable String birthDate, @PathVariable String color, @PathVariable long ownerId) {
        if (businessOwner.getOwnerById(ownerId) == null)
        {
            message = "No such owner";
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/err/err-info");
            return redirectView;
        }
        ServiceCat cat = new ServiceCat();
        cat.setServiceCat_name(name);
        cat.setServiceCat_breed(breed);
        cat.setColor(Color.valueOf(color.toUpperCase()));
        try {
            Date date = new SimpleDateFormat("dd-MM-yyyy").parse(birthDate);
            cat.setServiceCat_birth_date(date);
            businessCat.createCat(cat);
            ServiceOwner serviceOwner1 = businessOwner.getOwnerById(ownerId);
            businessCat.changeCatOwner(cat, serviceOwner1);
            message = "Cat " + cat.getServiceCat_name()  + " was successfully created for owner " + serviceOwner1.getName();
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/suc/suc-info");
            return redirectView;
        }
        catch (ParseException e){
            message = "Error. Wrong date format. Follow dd-MM-yyyy";
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/err/err-info");
            return redirectView;
        }
    }

    @Override
    @GetMapping(value = "/delete-cat/{catId}", produces = APPLICATION_JSON_VALUE)
    public RedirectView deleteCat(@PathVariable long catId) {
        if (businessCat.getCatById(catId) == null)
        {
            message =  "No such cat";
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/err/err-info");
            return redirectView;
        }
        ServiceCat serviceCat = businessCat.getCatById(catId);
        businessCat.deleteCat(serviceCat);
        message =  "Cat: \n name - " + serviceCat.getServiceCat_name() + " was deleted";
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/suc/suc-info");
        return redirectView;
    }

    @Override
    @GetMapping(value = "/start", produces = APPLICATION_JSON_VALUE)
    public String start() {
        return "Hi, " +  username +
                "\nYour cats: \n"
                + businessOwner.getOwnedCats(username)
                + " \n list of commands: \n /start \n /create-cat \n /delete-cat \n /cat-info \n /delete-owner \n /make-friends";
    }

    @Override
    @GetMapping(value = "/cat-info/{catId}")
    public RedirectView getCat(@PathVariable long catId) {
        if (businessCat.getCatById(catId) == null) {
            message = "No such cat";
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/err/err-info");
            return redirectView;
        }
        ServiceCat serviceCat = businessCat.getCatById(catId);
                message = "Your cat: \n name - " + serviceCat.getServiceCat_name() +
                        "\n breed - " + serviceCat.getServiceCat_breed() +
                        "\n Color - " + serviceCat.getColor() +
                        "\n birthdate - " + serviceCat.getServiceCat_birth_date() +
                        "\n his friends : \n" + businessCat.getFriends(serviceCat);
                RedirectView redirectView = new RedirectView();
                redirectView.setUrl("/suc/suc-info");
                return redirectView;
            }

    @GetMapping(value = "/delete-owner/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public RedirectView deleteOwner(@PathVariable long ownerId) {
        if (businessOwner.getOwnerById(ownerId) == null)
        {
            message = "No such owner";
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/err/err-info");
            return redirectView;
        }
        message = "Owner " + businessOwner.getOwnerById(ownerId).getName() + " was deleted";
        businessOwner.deleteOwnerById(ownerId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/suc/suc-info");
        return redirectView;
    }

    @GetMapping(value = "/make-friends/{id1}/{id2}")
    public RedirectView makeFriends(@PathVariable String id1, @PathVariable String id2) {
        long catId = Long.valueOf(id1).longValue();
        long catFriendId = Long.valueOf(id2).longValue();
        if (businessCat.getCatById(catId) == null || businessCat.getCatById(catFriendId) == null)
        {
            message = "No such cat";
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl("/err/err-info");
            return redirectView;
        }
        ServiceCat cat = businessCat.getCatById(catId);
        ServiceCat catFriend = businessCat.getCatById(catFriendId);
        businessCat.addCatFriend(cat, catFriend);
        message = "Cat " +
                cat.getServiceCat_name() +
                " and cat "
                + catFriend.getServiceCat_name() +
                " are friends now";
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/err/err-info");
        return redirectView;
    }
}