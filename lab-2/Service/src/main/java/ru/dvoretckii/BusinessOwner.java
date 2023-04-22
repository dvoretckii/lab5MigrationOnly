package ru.dvoretckii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Set<ServiceCat> getOwnedCats(ServiceOwner serviceOwner) {
        Owner owner = ownerRepository.getById(serviceOwner.getOwner_id());
        Set<ServiceCat> serviceCats = new HashSet<>();
        BusinessCat businessCat = null;
        for (Cat friend:
                owner.getOwnedCats()) {
            ServiceCat serviceCat1 = businessCat.getCatById(friend.getCat_id());
            serviceCats.add(serviceCat1);
        }
        return serviceCats;
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
