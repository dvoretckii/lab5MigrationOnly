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
public class BusinessOwner {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CatRepository catRepository;
    public void createOwner(ServiceOwner serviceOwner) {
        Owner owner = new Owner();
        owner.setName(serviceOwner.getName());
        owner.setOwner_birth_date(serviceOwner.getOwner_birth_date());
        ownerRepository.saveAndFlush(owner);
        serviceOwner.setOwner_id(owner.getOwner_id());
    }

    public ServiceOwner getOwnerById(long id) {
        Owner owner = ownerRepository.getById(id);
        ServiceOwner serviceOwner = new ServiceOwner();
        serviceOwner.setName(owner.getName());
        serviceOwner.setOwner_birth_date(owner.getOwner_birth_date());
        serviceOwner.setOwner_id(owner.getOwner_id());
        return serviceOwner;
    }

    public String getOwnedCats(ServiceOwner serviceOwner) {
        Owner owner = ownerRepository.getById(serviceOwner.getOwner_id());
        Set<ServiceCat> serviceCats = new HashSet<>();
        for (Cat friend:
                owner.getOwnedCats()) {
            Cat cat = catRepository.getById(friend.getCat_id());
            ServiceCat serviceCat = new ServiceCat();
            serviceCat.setServiceCat_id(cat.getCat_id());
            serviceCat.setServiceCat_breed(cat.getCat_breed());
            serviceCat.setServiceCat_name(cat.getCat_name());
            serviceCat.setServiceCat_birth_date(cat.getCat_birth_date());
            serviceCats.add(serviceCat);
        }
        String cats = "Cats:\n";
        for (ServiceCat cat:
             serviceCats) {
            cats += cat.getServiceCat_name() + " ";
        }
        return cats;
    }
    public void deleteOwnerById(long id) {
        Owner owner = ownerRepository.getById(id);
        Set<Cat> cats = owner.getOwnedCats();
        BusinessCat businessCat = null;
        for (Cat cat:
             cats) {
            ServiceCat serviceCat = businessCat.getCatById(cat.getCat_id());
            businessCat.deleteCatFromOwner(serviceCat);
        }
        ownerRepository.deleteById(id);
    }
}
