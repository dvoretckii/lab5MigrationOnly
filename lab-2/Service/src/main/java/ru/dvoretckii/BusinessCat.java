package ru.dvoretckii;

import ru.dvoretckii.Sessions.CatSession;
import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Entities.Owner;

import java.util.Set;

public class BusinessCat {
    public void createCat(Cat cat) {
        new CatSession().createCat(cat);
    }

    public void deleteCat(Cat cat) {
        new CatSession().deleteCat(cat);
    }
    public void changeCatOwner(Cat cat, Owner owner) {
        cat.setOwner(owner);
        new CatSession().updateCat(cat);
    }

    public void deleteCatFromOwner(Cat cat) {
        changeCatOwner(cat, null);
    }

    public void setFriends(Cat cat, Set<Cat> catsFriends) {
        cat.setFriends(catsFriends);
        new CatSession().updateCat(cat);
    }
    public void removeCatFriend(Cat cat, Cat friendCat) {
        Set<Cat> catsFriends = cat.getFriends();
        catsFriends.remove(friendCat);
        setFriends(cat, catsFriends);
    }

    public void addCatFriend(Cat cat, Cat friendCat) {
        Set<Cat> catsFriends = cat.getFriends();
        catsFriends.add(friendCat);
        setFriends(cat, catsFriends);
    }
}
