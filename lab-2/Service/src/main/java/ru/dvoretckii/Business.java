package ru.dvoretckii;

import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Entities.Owner;

import java.util.Set;

public class Business {
    public void createCat(Cat cat) {
        BusinessCat businessCat = new BusinessCat();
        BusinessOwner businessOwner = new BusinessOwner();
        businessCat.createCat(cat);
        Owner owner = cat.getOwner();
        if (owner != null) {
            businessOwner.addNewCatToOwner(owner, cat);
        }
        Set<Cat> cats = cat.getFriends();
        for (Cat friendCat : cats) {
            businessCat.addCatFriend(friendCat, cat);
        }
    }

    public void createOwner(Owner owner) {
        BusinessCat businessCat = new BusinessCat();
        BusinessOwner businessOwner = new BusinessOwner();
        businessOwner.createOwner(owner);
        Set<Cat> cats = owner.getOwnedCats();
        for (Cat cat : cats) {
            businessCat.changeCatOwner(cat, owner);
        }
    }

    public void deleteCat(Cat cat) {
        BusinessCat businessCat = new BusinessCat();
        BusinessOwner businessOwner = new BusinessOwner();
        Owner owner = cat.getOwner();
        if (owner != null) {
            businessOwner.removeCatFromOwner(owner, cat);
        }
        Set<Cat> cats = cat.getFriends();
        for (Cat friendCat : cats) {
            businessCat.removeCatFriend(friendCat, cat);
        }
        businessCat.deleteCat(cat);
    }

    public void deleteOwner(Owner owner) {
        BusinessCat businessCat = new BusinessCat();
        BusinessOwner businessOwner = new BusinessOwner();
        Set<Cat> cats = owner.getOwnedCats();
        for (Cat cat : cats) {
            businessCat.deleteCatFromOwner(cat);
        }
        businessOwner.deleteOwner(owner);
    }

    public void addCatToOwner(Cat cat, Owner owner) {
        BusinessCat businessCat = new BusinessCat();
        BusinessOwner businessOwner = new BusinessOwner();
        businessCat.changeCatOwner(cat, owner);
        businessOwner.addNewCatToOwner(owner, cat);
    }

    public void deleteCatFromOwner(Cat cat) {
        BusinessCat businessCat = new BusinessCat();
        BusinessOwner businessOwner = new BusinessOwner();
        Owner owner = cat.getOwner();
        businessCat.deleteCatFromOwner(cat);
        businessOwner.removeCatFromOwner(owner, cat);
    }

    public void changeCatOwner(Cat cat, Owner owner) {
        Owner oldOwner = cat.getOwner();
        deleteCatFromOwner(cat);
        addCatToOwner(cat, owner);
    }

    public void addCatFriend(Cat cat, Cat friend) {
        BusinessCat businessCat = new BusinessCat();
        businessCat.addCatFriend(cat, friend);
        businessCat.addCatFriend(friend, cat);
    }

    public void removeCatFriend(Cat cat, Cat friend) {
        BusinessCat businessCat = new BusinessCat();
        businessCat.removeCatFriend(cat, friend);
        businessCat.removeCatFriend(friend, cat);
    }
}
