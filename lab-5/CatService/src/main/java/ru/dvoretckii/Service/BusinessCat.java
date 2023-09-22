package ru.dvoretckii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.dvoretckii.DAO.Entities.Cat;
import ru.dvoretckii.DAO.Repositories.CatRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class BusinessCat {

    private final CatRepository catRepository;
    @Autowired
    public BusinessCat(CatRepository catRepository) {
        this.catRepository = catRepository;
    }


    //    @Autowired
//    private OwnerRepository ownerRepository;
    public ServiceCat createCat(ServiceCat serviceCat) {
        Cat cat = new Cat();
        cat.setCat_name(serviceCat.getServiceCat_name());
        cat.setCat_breed(serviceCat.getServiceCat_breed());
        cat.setColor(serviceCat.getColor());
        cat.setCat_birth_date(serviceCat.getServiceCat_birth_date());
        cat.setOwner(serviceCat.getServiceOwner());
        catRepository.saveAndFlush(cat);
        ServiceCat serviceCat1 = new ServiceCat();
        serviceCat1.setServiceCat_id(cat.getCat_id());
        serviceCat1.setColor(cat.getColor());
        serviceCat1.setServiceCat_breed(cat.getCat_breed());
        serviceCat1.setServiceCat_name(cat.getCat_name());
        serviceCat1.setServiceCat_birth_date(serviceCat.getServiceCat_birth_date());
        serviceCat1.setServiceOwner(serviceCat.getServiceOwner());
        return serviceCat1;
    }

    public ServiceCat updateCat(ServiceCat serviceCat) {
        Cat cat = catRepository.myGetById(serviceCat.getServiceCat_id());
        cat.setCat_name(serviceCat.getServiceCat_name());
        cat.setCat_breed(serviceCat.getServiceCat_breed());
        cat.setColor(serviceCat.getColor());
        cat.setCat_birth_date(serviceCat.getServiceCat_birth_date());
        cat.setOwner(serviceCat.getServiceOwner());
        catRepository.saveAndFlush(cat);
        ServiceCat serviceCat1 = new ServiceCat();
        serviceCat1.setServiceCat_id(cat.getCat_id());
        serviceCat1.setColor(cat.getColor());
        serviceCat1.setServiceCat_breed(cat.getCat_breed());
        serviceCat1.setServiceCat_name(cat.getCat_name());
        serviceCat1.setServiceCat_birth_date(serviceCat.getServiceCat_birth_date());
        serviceCat1.setServiceOwner(cat.getOwner());
        return serviceCat1;

    }

    public ServiceCat getCatById(long id) {
        Cat cat = catRepository.myGetById(id);
        ServiceCat serviceCat = new ServiceCat();
        serviceCat.setServiceCat_breed(cat.getCat_breed());
        serviceCat.setServiceCat_name(cat.getCat_name());
        serviceCat.setServiceCat_birth_date(cat.getCat_birth_date());
        serviceCat.setServiceCat_id(cat.getCat_id());
        serviceCat.setColor(cat.getColor());
        return serviceCat;
    }

    public Set<ServiceCat> getFriends(ServiceCat serviceCat) {
        Cat cat = catRepository.myGetById(serviceCat.getServiceCat_id());
        Set<ServiceCat> friends = new HashSet<>();
        for (Cat friend :
                cat.getFriends()) {
            ServiceCat serviceCat1 = getCatById(friend.getCat_id());
            friends.add(serviceCat1);
        }
        return friends;
    }

    public void deleteCatsFromOwner(Long owner) {
        List<Cat> ownedCats = catRepository.getOwnedCats(owner);
        for (Cat cat: ownedCats) {
            cat.setOwner(null);
            catRepository.saveAndFlush(cat);
        }
    }

    public List<String> getOwnedCats(Long owner) {
        List<Cat> ownedCats = catRepository.getOwnedCats(owner);
        List<String> cats = new ArrayList<>();
        for (Cat cat: ownedCats) {
            cats.add(cat.getCat_name());
        }
        return cats;
    }


//    public void changeCatOwner(ServiceCat serviceCat, ServiceOwner serviceOwner) {
//        deleteCatFromOwner(serviceCat);
//        Owner owner = ownerRepository.getById(serviceOwner.getOwner_id());
//        Set<Cat> cats = owner.getOwnedCats();
//        Cat cat = catRepository.getById(serviceCat.getServiceCat_id());
//        cats.add(cat);
//        owner.setOwnedCats(cats);
//        cat.setOwner(owner);
//        ownerRepository.saveAndFlush(owner);
//        catRepository.saveAndFlush(cat);
//    }

    public void addCatFriend(ServiceCat serviceCat, ServiceCat serviceFriend) {
        Cat cat = catRepository.myGetById(serviceCat.getServiceCat_id());
        Cat friend = catRepository.myGetById(serviceFriend.getServiceCat_id());
        Set<Cat> catsFriends = cat.getFriends();
        Set<Cat> friendsFriends = friend.getFriends();
        catsFriends.add(friend);
        friendsFriends.add(cat);
        catRepository.saveAndFlush(cat);
        catRepository.saveAndFlush(friend);
    }

    public void deleteCat(Long id) {
        Cat cat = catRepository.myGetById(id);
        Set<Cat> catsFriends = cat.getFriends();
        for (Cat friend :
                catsFriends) {
            Set<Cat> friendFriends = friend.getFriends();
            friendFriends.remove(cat);
            friend.setFriends(friendFriends);
            catRepository.saveAndFlush(friend);
        }
        catRepository.deleteById(id);
    }
}
