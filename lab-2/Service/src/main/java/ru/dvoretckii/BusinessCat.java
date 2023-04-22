package ru.dvoretckii;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dvoretckii.Repositories.CatRepository;
import ru.dvoretckii.Repositories.OwnerRepository;
import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Entities.Owner;
import ru.dvoretckii.responses.ServiceCat;
import ru.dvoretckii.responses.ServiceOwner;

import java.util.HashSet;
import java.util.Set;
@Service
public class BusinessCat {
    @Autowired
    private CatRepository catRepository;
    @Autowired
    private OwnerRepository ownerRepository;
    public void createCat(ServiceCat serviceCat) {
        Cat cat = new Cat();
        cat.setCat_name(serviceCat.getServiceCat_name());
        cat.setCat_breed(serviceCat.getServiceCat_breed());
        cat.setColor(serviceCat.getColor());
        cat.setCat_birth_date(serviceCat.getServiceCat_birth_date());
        catRepository.saveAndFlush(cat);
        serviceCat.setServiceCat_id(cat.getCat_id());
    }

    public ServiceCat getCatById(long id) {
        Cat cat = catRepository.getById(id);
        ServiceCat serviceCat = new ServiceCat();
        serviceCat.setServiceCat_breed(cat.getCat_breed());
        serviceCat.setServiceCat_name(cat.getCat_name());
        serviceCat.setServiceCat_birth_date(cat.getCat_birth_date());
        serviceCat.setServiceCat_id(cat.getCat_id());
        serviceCat.setColor(cat.getColor());
        return serviceCat;
    }

    public Set<ServiceCat> getFriends(ServiceCat serviceCat) {
        Cat cat = catRepository.getById(serviceCat.getServiceCat_id());
        Set<ServiceCat> friends = new HashSet<>();
        for (Cat friend:
             cat.getFriends()) {
            ServiceCat serviceCat1 = getCatById(friend.getCat_id());
            friends.add(serviceCat1);
        }
        return friends;
    }

    public void deleteCatFromOwner(ServiceCat serviceCat) {
        if (serviceCat.getServiceOwner() != null) {
        Owner owner = ownerRepository.getById(serviceCat.getServiceOwner().getOwner_id());
        Set<Cat> cats = owner.getOwnedCats();
        for (Cat cat:
             cats) {
            if (cat.getCat_id() == serviceCat.getServiceCat_id()) {
                cats.remove(cat);
                owner.setOwnedCats(cats);
                ownerRepository.saveAndFlush(owner);
                cat.setOwner(null);
                catRepository.saveAndFlush(cat);
                return;
            }
        }
        }
    }
    public void changeCatOwner(ServiceCat serviceCat, ServiceOwner serviceOwner) {
        deleteCatFromOwner(serviceCat);
        Owner owner = ownerRepository.getById(serviceOwner.getOwner_id());
        Set<Cat> cats = owner.getOwnedCats();
        Cat cat = catRepository.getById(serviceCat.getServiceCat_id());
        cats.add(cat);
        owner.setOwnedCats(cats);
        cat.setOwner(owner);
        ownerRepository.saveAndFlush(owner);
        catRepository.saveAndFlush(cat);
    }
    public void removeCatFriend(ServiceCat serviceCat, ServiceCat serviceFriend) {
        Cat cat = catRepository.getById(serviceCat.getServiceCat_id());
        Cat friend = catRepository.getById(serviceFriend.getServiceCat_id());
        Set<Cat> catsFriends = cat.getFriends();
        Set<Cat> friendsFriends = friend.getFriends();
        catsFriends.remove(friend);
        friendsFriends.remove(cat);
        catRepository.saveAndFlush(cat);
        catRepository.saveAndFlush(friend);
    }

    public void addCatFriend(ServiceCat serviceCat, ServiceCat serviceFriend) {
        Cat cat = catRepository.getById(serviceCat.getServiceCat_id());
        Cat friend = catRepository.getById(serviceFriend.getServiceCat_id());
        Set<Cat> catsFriends = cat.getFriends();
        Set<Cat> friendsFriends = friend.getFriends();
        catsFriends.add(friend);
        friendsFriends.add(cat);
        catRepository.saveAndFlush(cat);
        catRepository.saveAndFlush(friend);
    }

    public void deleteCat(ServiceCat serviceCat) {
        long id = serviceCat.getServiceCat_id();
        Cat cat = catRepository.getById(id);
        if (cat.getOwner() != null) {
            deleteCatFromOwner(serviceCat);
        }
        Set<Cat> catsFriends = cat.getFriends();
        for (Cat friend:
             catsFriends) {
            Set<Cat> friendFriends = friend.getFriends();
            friendFriends.remove(cat);
            friend.setFriends(friendFriends);
            catRepository.saveAndFlush(friend);
        }
        catRepository.deleteById(id);
    }
}
