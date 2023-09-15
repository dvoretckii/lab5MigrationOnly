package ru.dvoretckii.ServiceManager;

import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Entities.Owner;
import ru.dvoretckii.EntityManager.CatEntityManager;
import ru.dvoretckii.EntityManager.OwnerEntityManager;
import ru.dvoretckii.OOPEntities.CatOOP;
import ru.dvoretckii.OOPEntities.OwnerOOP;
import ru.dvoretckii.Sessions.CatSession;

import java.util.HashSet;
import java.util.Set;
public class CatManager {
    public void createCat(CatOOP serviceCat) {
        Cat cat = new Cat();
        cat.setCat_name(serviceCat.getServiceCat_name());
        cat.setCat_breed(serviceCat.getServiceCat_breed());
        cat.setColor(serviceCat.getColor());
        cat.setCat_birth_date(serviceCat.getCat_birth_date());
        CatEntityManager em = new CatEntityManager();
        em.createCat(cat);
        serviceCat.setServiceCat_id(cat.getCat_id());
    }

    public CatOOP getCatById(long id) {
        Cat cat = new CatEntityManager().readCat(id);
        CatOOP serviceCat = new CatOOP();
        serviceCat.setServiceCat_breed(cat.getCat_breed());
        serviceCat.setServiceCat_name(cat.getCat_name());
        serviceCat.setCat_birth_date(cat.getCat_birth_date());
        serviceCat.setServiceCat_id(cat.getCat_id());
        serviceCat.setColor(cat.getColor());
        return serviceCat;
    }

    public Set<CatOOP> getFriends(CatOOP serviceCat) {
        Cat cat = new CatEntityManager().readCat(serviceCat.getServiceCat_id());
        Set<CatOOP> friends = new HashSet<>();
        for (Cat friend:
                cat.getFriends()) {
            CatOOP serviceCat1 = getCatById(friend.getCat_id());
            friends.add(serviceCat1);
        }
        return friends;
    }

    public void deleteCatFromOwner(CatOOP serviceCat) {
        if (serviceCat.getOwnerOOP() != null) {
            Owner owner = new OwnerEntityManager().readOwner(serviceCat.getOwnerOOP().getOwner_id());
            Set<Cat> cats = owner.getOwnedCats();
            for (Cat cat:
                    cats) {
                if (cat.getCat_id() == serviceCat.getServiceCat_id()) {
                    cats.remove(cat);
                    owner.setOwnedCats(cats);
                    new OwnerEntityManager().updateOwner(owner);
                    cat.setOwner(null);
                    new CatEntityManager().updateCat(cat);
                    return;
                }
            }
        }
    }
    public void changeCatOwner(CatOOP serviceCat, OwnerOOP serviceOwner) {
        deleteCatFromOwner(serviceCat);
        Owner owner = new OwnerEntityManager().readOwner(serviceOwner.getOwner_id());
        Set<Cat> cats = owner.getOwnedCats();
        Cat cat = new CatEntityManager().readCat(serviceCat.getServiceCat_id());
        cats.add(cat);
        owner.setOwnedCats(cats);
        cat.setOwner(owner);
        new CatEntityManager().updateCat(cat);
        new OwnerEntityManager().updateOwner(owner);
    }
    public void removeCatFriend(CatOOP serviceCat, CatOOP serviceFriend) {
        Cat cat = new CatEntityManager().readCat(serviceCat.getServiceCat_id());
        Cat friend = new CatEntityManager().readCat(serviceFriend.getServiceCat_id());
        Set<Cat> catsFriends = cat.getFriends();
        Set<Cat> friendsFriends = friend.getFriends();
        catsFriends.remove(friend);
        friendsFriends.remove(cat);
        new CatEntityManager().updateCat(cat);
        new CatEntityManager().updateCat(friend);
    }

    public void addCatFriend(CatOOP serviceCat, CatOOP serviceFriend) {
        Cat cat = new CatEntityManager().readCat(serviceCat.getServiceCat_id());
        Cat friend = new CatEntityManager().readCat(serviceFriend.getServiceCat_id());
        Set<Cat> catsFriends = cat.getFriends();
        Set<Cat> friendsFriends = friend.getFriends();
        catsFriends.add(friend);
        friendsFriends.add(cat);
        new CatEntityManager().updateCat(cat);
        new CatEntityManager().updateCat(friend);
    }

    public void deleteCat(CatOOP serviceCat) {
        long id = serviceCat.getServiceCat_id();
        Cat cat = new CatEntityManager().readCat(id);
        if (cat.getOwner() != null) {
            deleteCatFromOwner(serviceCat);
        }
        Set<Cat> catsFriends = cat.getFriends();
        for (Cat friend:
                catsFriends) {
            Set<Cat> friendFriends = friend.getFriends();
            friendFriends.remove(cat);
            friend.setFriends(friendFriends);
            new CatEntityManager().updateCat(friend);
        }
        new CatEntityManager().deleteCat(cat);
    }
}
