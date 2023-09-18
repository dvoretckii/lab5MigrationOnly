package ru.dvoretckii.OwnerControllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

public interface OwnerControllerInterface {

    RedirectView createCat(String name, String breed, String birthDate, String color, long ownerId);
    RedirectView deleteCat(long catId);
    String start();
    RedirectView getCat(long id);
}
