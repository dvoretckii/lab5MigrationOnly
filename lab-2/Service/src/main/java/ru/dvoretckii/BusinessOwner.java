package ru.dvoretckii;

import ru.dvoretckii.Sessions.OwnerSession;
import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Entities.Owner;

import java.util.HashSet;
import java.util.Set;

public class BusinessOwner {
    public void createOwner(Owner owner) {
        new OwnerSession().createOwner(owner);
    }

    public void setCats(Owner owner, Set<Cat> cats) {
        owner.setOwnedCats(cats);
        new OwnerSession().updateOwner(owner);
    }
    public void addNewCatToOwner(Owner owner, Cat newCat) {
        Set<Cat> cats = new HashSet<>(owner.getOwnedCats());
        cats.add(newCat);
        setCats(owner, cats);
    }

    public void removeCatFromOwner(Owner owner, Cat cat){
        Set<Cat> cats = new HashSet<>(owner.getOwnedCats());
        cats.remove(cat);
        setCats(owner, cats);
    }
    
    public void deleteOwner(Owner owner) {
        new OwnerSession().deleteOwner(owner);
    }
}
