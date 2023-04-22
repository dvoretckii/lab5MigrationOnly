package ru.dvoretckii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dvoretckii.Entities.Color;
import ru.dvoretckii.responses.ServiceCat;
import ru.dvoretckii.responses.ServiceOwner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/cat")
public class CatController {
    BusinessCat businessCat;
    BusinessOwner businessOwner;

    @Autowired
    public CatController(BusinessCat businessCat, BusinessOwner businessOwner) {
        this.businessCat = businessCat;
        this.businessOwner = businessOwner;
    }
    @GetMapping(value = "get/{catId}")
    public String getCatById(@PathVariable long catId) {
        if (businessCat.getCatById(catId) == null)
        {
            return "No such cat";
        }
        ServiceCat cat = businessCat.getCatById(catId);
        return cat.toString();
    }

    @GetMapping(value = "/create/{name}/{breed}/{birthDate}/{color}/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public String create(@PathVariable String name,@PathVariable String breed,@PathVariable String birthDate,@PathVariable String color, @PathVariable long ownerId) {
        ServiceCat cat = new ServiceCat();
        cat.setServiceCat_name(name);
        cat.setServiceCat_breed(breed);
        cat.setColor(Color.valueOf(color.toUpperCase()));
        Date d;
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            d = f.parse(birthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        cat.setServiceCat_birth_date(d);
        businessCat.createCat(cat);
        ServiceOwner serviceOwner = businessOwner.getOwnerById(ownerId);
        businessCat.changeCatOwner(cat, serviceOwner);
        return "Cat was created. " + cat.toString();
    }


    @GetMapping(value = "/delete/{catId}", produces = APPLICATION_JSON_VALUE)
    public String deleteCat(@PathVariable long catId) {
        if (businessCat.getCatById(catId) == null)
        {
            return "No such cat";
        }
        ServiceCat cat = businessCat.getCatById(catId);
        businessCat.deleteCat(cat);
        return "Cat " +
                cat.getServiceCat_name() +
                " was deleted";
    }

    @GetMapping(value = "/friend/{id1}/{id2}")
    public String makeFriends(@PathVariable String id1, @PathVariable String id2) {
        long catId = Long.valueOf(id1).longValue();
        long catFriendId = Long.valueOf(id2).longValue();
        if (businessCat.getCatById(catId) == null || businessCat.getCatById(catFriendId) == null)
        {
            return "No such cat";
        }
        ServiceCat cat = businessCat.getCatById(catId);
        ServiceCat catFriend = businessCat.getCatById(catFriendId);
        businessCat.addCatFriend(cat, catFriend);
        return "Cat " +
                cat.getServiceCat_name() +
                " and cat "
                + catFriend.getServiceCat_name() +
                " are friends now";
    }


    @GetMapping(value = "/owner/{catId}/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public String ownCat(@PathVariable long catId, @PathVariable long ownerId) {
        if (businessCat.getCatById(catId) == null)
        {
            return "No such cat";
        }

        if (businessOwner.getOwnerById(ownerId) == null)
        {
            return "No such owner";
        }
        ServiceCat cat = businessCat.getCatById(catId);
        ServiceOwner serviceOwner = businessOwner.getOwnerById(ownerId);
        businessCat.changeCatOwner(cat, serviceOwner);
        return "Cat " +
                cat.getServiceCat_name() +
                " was owned by " +
                serviceOwner.getName();
    }

    @GetMapping(value = "/health")
    public String hello()
    {
        return "ok";
    }
}