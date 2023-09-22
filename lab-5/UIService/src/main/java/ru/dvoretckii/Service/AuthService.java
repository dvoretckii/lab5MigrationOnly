package ru.dvoretckii.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.dvoretckii.DAO.AuthRepository;
import ru.dvoretckii.DAO.Entities.Owner;
import ru.dvoretckii.DAO.Entities.Role;
import ru.dvoretckii.DAO.Repository.OwnerRepository;

import java.security.SecureRandom;
import java.util.Collections;

@Component
public class AuthService implements UserDetailsService {

        private AuthRepository authRepository;

        private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthRepository r) {
        this.authRepository = r;
        this.passwordEncoder = new BCryptPasswordEncoder(11, new SecureRandom());
    }

    public AuthService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Owner owner = authRepository.getByName(username);
            ServiceOwner serviceOwner = new ServiceOwner();
            serviceOwner.setOwner_id(owner.getOwner_id());
            serviceOwner.setOwner_birth_date(owner.getOwner_birth_date());
            serviceOwner.setName(owner.getUsername());
            serviceOwner.setRoles(owner.getRoles());
            serviceOwner.setPassword(owner.getPassword());
            return serviceOwner;
        }catch (Exception ignored){
        }

        return null;
    }

    public String findByUsername(String username) {
        if (authRepository.getByName(username) == null) {
            return "No such user. If u want to register try /registration?name=yourusername&password=yourpassword";
        }
        else {
            return "Wrong password. Return to login page : /login" ;
        }
    }

    public void saveUser(ServiceOwner serviceOwner) {
        Owner owner = new Owner();
        owner.setName(serviceOwner.getName());
        owner.setOwner_birth_date(serviceOwner.getOwner_birth_date());
        owner.setRoles(Collections.singleton(new Role(2L)));
        System.out.println(serviceOwner.getPassword());
        owner.setPassword(passwordEncoder.encode(serviceOwner.getPassword()));
        System.out.println(owner.getPassword());
        authRepository.saveAndFlush(owner);
        serviceOwner.setOwner_id(owner.getOwner_id());
    }
}
