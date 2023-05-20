package ru.dvoretckii;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dvoretckii.responses.ServiceCat;
import ru.dvoretckii.responses.ServiceOwner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    BusinessOwner businessOwner;
    BusinessCat businessCat;

    public OwnerController(BusinessOwner businessOwner) {
        this.businessOwner = businessOwner;
    }

    @GetMapping(value = "get/{ownerId}")
    public String getCatById(@PathVariable long ownerId) {
        if (businessOwner.getOwnerById(ownerId) == null)
        {
            return "No such owner";
        }
        ServiceOwner owner = businessOwner.getOwnerById(ownerId);
        return owner.toString();
    }

    @GetMapping(value = "/create/{name}/{birthDate}", produces = APPLICATION_JSON_VALUE)
    public String create(@PathVariable String name,@PathVariable String birthDate) {
        ServiceOwner owner = new ServiceOwner();
        owner.setName(name);
        Date d;
        SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            d = f.parse(birthDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        owner.setOwner_birth_date(d);
        businessOwner.createOwner(owner);
        return "Owner was created. " + owner.toString();
    }

    @GetMapping(value = "/addCat/{ownerId}/{catId}", produces = APPLICATION_JSON_VALUE)
    public String create(@PathVariable Long ownerId,@PathVariable Long catId) {
        ServiceOwner serviceOwner = businessOwner.getOwnerById(ownerId);
        ServiceCat serviceCat = businessCat.getCatById(catId);
        return "hui";
    }

    @GetMapping(value = "/delete/{ownerId}", produces = APPLICATION_JSON_VALUE)
    public String deleteCat(@PathVariable long ownerId) {
        if (businessOwner.getOwnerById(ownerId) == null)
        {
            return "No such cat";
        }
        ServiceOwner owner = businessOwner.getOwnerById(ownerId);
        businessOwner.deleteOwnerById(ownerId);
        return "Owner " +
                owner.getName() +
                " was deleted";
    }
}
