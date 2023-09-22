package ru.dvoretckii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.dvoretckii.DAO.Entities.Owner;
import ru.dvoretckii.DAO.Entities.Role;
import ru.dvoretckii.DAO.Repository.OwnerRepository;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class BusinessOwner {
    @Autowired
    private OwnerRepository ownerRepository;

    private final PasswordEncoder passwordEncoder;

    public BusinessOwner() {
        passwordEncoder = new BCryptPasswordEncoder(11, new SecureRandom());
    }

    public BusinessOwner(PasswordEncoder encoder) {
        passwordEncoder = encoder;
    }

    public ServiceOwner getOwnerById(long id) {
        Owner owner = ownerRepository.myGetById(id);
        ServiceOwner serviceOwner = new ServiceOwner();
       // serviceOwner.setName(owner.getUsername());
        serviceOwner.setOwner_birth_date(owner.getOwner_birth_date());
        serviceOwner.setOwner_id(owner.getOwner_id());
        //serviceOwner.setPassword(owner.getPassword());
        //serviceOwner.setRoles(owner.getAuthorities());
        return serviceOwner;
    }

    public ServiceOwner updateOwner(ServiceOwner serviceOwner) {
        Owner owner = ownerRepository.myGetById(serviceOwner.getOwner_id());
        owner.setOwner_birth_date(serviceOwner.getOwner_birth_date());
        ownerRepository.saveAndFlush(owner);
        ServiceOwner serviceOwner1 = new ServiceOwner();
        serviceOwner1.setOwner_id(owner.getOwner_id());
        serviceOwner1.setOwner_birth_date(serviceOwner.getOwner_birth_date());
        return serviceOwner1;
    }

//    public Set<ServiceCat> getOwnedCats(String name) {
//        Owner owner = ownerRepository.getByName(name);
//        Set<ServiceCat> serviceCats = new HashSet<>();
//        for (Cat friend:
//                owner.getOwnedCats()) {
//            Cat cat = catRepository.getById(friend.getCat_id());
//            ServiceCat serviceCat = new ServiceCat();
//            serviceCat.setServiceCat_id(cat.getCat_id());
//            serviceCat.setServiceCat_breed(cat.getCat_breed());
//            serviceCat.setServiceCat_name(cat.getCat_name());
//            serviceCat.setServiceCat_birth_date(cat.getCat_birth_date());
//            serviceCats.add(serviceCat);
//        }
//        String cats = "Cats:\n";
//        for (ServiceCat cat:
//                serviceCats) {
//            cats += cat.getServiceCat_name() + " ";
//        }
//        return serviceCats;
//    }
    public void deleteOwner(long id) {
        ownerRepository.deleteById(id);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        try {
//            Owner owner = ownerRepository.getByName(username);
//            ServiceOwner serviceOwner = new ServiceOwner();
//            serviceOwner.setOwner_id(owner.getOwner_id());
//            serviceOwner.setServiceOwner_birth_date(owner.getOwner_birth_date());
//            serviceOwner.setName(owner.getUsername());
//            serviceOwner.setRoles(owner.getRoles());
//            serviceOwner.setPassword(owner.getPassword());
//            return serviceOwner;
//        }catch (Exception ignored){
//        }
//
//        return null;
//    }

//    public String findByUsername(String username) {
//        if (ownerRepository.getByName(username) == null) {
//            return "No such user. If u want to register try /registration?name=yourusername&password=yourpassword";
//        }
//        else {
//            return "Wrong password. Return to login page : /login" ;
//        }
//    }

//    public void saveUser(ServiceOwner serviceOwner) {
//        Owner owner = new Owner();
//        owner.setName(serviceOwner.getName());
//        owner.setOwner_birth_date(serviceOwner.getOwner_birth_date());
//        owner.setRoles(Collections.singleton(new Role(2L)));
//        System.out.println(serviceOwner.getPassword());
//        owner.setPassword(passwordEncoder.encode(serviceOwner.getPassword()));
//        System.out.println(owner.getPassword());
//        ownerRepository.saveAndFlush(owner);
//        serviceOwner.setOwner_id(owner.getOwner_id());
//    }
}
