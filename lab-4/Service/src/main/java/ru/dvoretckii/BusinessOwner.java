package ru.dvoretckii;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Entities.Owner;
import ru.dvoretckii.Entities.Role;
import ru.dvoretckii.Repositories.CatRepository;
import ru.dvoretckii.Repositories.OwnerRepository;
import ru.dvoretckii.responses.ServiceCat;
import ru.dvoretckii.responses.ServiceOwner;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Service
public class BusinessOwner implements UserDetailsService {
    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private CatRepository catRepository;

    private final PasswordEncoder passwordEncoder;

    public BusinessOwner() {
        passwordEncoder = new BCryptPasswordEncoder(11, new SecureRandom());
    }

    public BusinessOwner(PasswordEncoder encoder) {
        passwordEncoder = encoder;
    }


    public void createOwner(ServiceOwner serviceOwner) {
        Owner owner = new Owner();
        owner.setName(serviceOwner.getName());
        owner.setOwner_birth_date(serviceOwner.getOwner_birth_date());
        ownerRepository.saveAndFlush(owner);
        serviceOwner.setOwner_id(owner.getOwner_id());
    }

    public ServiceOwner getOwnerById(long id) {
        System.out.println("HUI1");
        Owner owner = ownerRepository.getById(id);
        System.out.println(owner.getOwner_id());
        ServiceOwner serviceOwner = new ServiceOwner();
        serviceOwner.setName(owner.getUsername());
        serviceOwner.setOwner_birth_date(owner.getOwner_birth_date());
        serviceOwner.setOwner_id(owner.getOwner_id());
        serviceOwner.setPassword(owner.getPassword());
        serviceOwner.setRoles(owner.getAuthorities());
        return serviceOwner;
    }

    public ServiceOwner getOwnerByName(String name) {
        Owner owner = ownerRepository.getByName(name);
        ServiceOwner serviceOwner = new ServiceOwner();
        serviceOwner.setName(owner.getUsername());
        serviceOwner.setOwner_birth_date(owner.getOwner_birth_date());
        serviceOwner.setOwner_id(owner.getOwner_id());
        serviceOwner.setPassword(owner.getPassword());
        serviceOwner.setRoles(owner.getAuthorities());
        return serviceOwner;
    }
    public String getOwnedCats(String name) {
        Owner owner = ownerRepository.getByName(name);
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepository.getByName(username);
        ServiceOwner serviceOwner = new ServiceOwner();
        serviceOwner.setOwner_id(owner.getOwner_id());
        serviceOwner.setServiceOwner_birth_date(owner.getOwner_birth_date());
        serviceOwner.setName(owner.getUsername());
        serviceOwner.setRoles(owner.getRoles());
        serviceOwner.setPassword(owner.getPassword());
        return serviceOwner;
    }

    public boolean saveUser(ServiceOwner serviceOwner) {
        Owner owner = new Owner();
        owner.setName(serviceOwner.getName());
        owner.setOwner_birth_date(serviceOwner.getOwner_birth_date());
        owner.setRoles(Collections.singleton(new Role(2L)));
        System.out.println(serviceOwner.getPassword());
        owner.setPassword(passwordEncoder.encode(serviceOwner.getPassword()));
        System.out.println(owner.getPassword());
        ownerRepository.saveAndFlush(owner);
        serviceOwner.setOwner_id(owner.getOwner_id());
        return true;
    }
}