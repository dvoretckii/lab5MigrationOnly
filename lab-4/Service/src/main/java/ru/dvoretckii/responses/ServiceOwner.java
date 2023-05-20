package ru.dvoretckii.responses;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.dvoretckii.Entities.Role;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ServiceOwner implements UserDetails {
    private Long owner_id;
    private String name;
    private Date serviceOwner_birth_date;
    private String password;
    private Set<Role> roles;


    @Override
    public String toString() {
        return "ServiceOwner{" +
                "owner_id=" + owner_id +
                ", name='" + name + '\'' +
                ", serviceOwner_birth_date=" + serviceOwner_birth_date +
                '}';
    }

    private Set<ServiceCat> ownedCats = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner_birth_date(Date owner_birth_date) {
        this.serviceOwner_birth_date = owner_birth_date;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public String getName() {
        return name;
    }

    public Set<ServiceCat> getOwnedCats() {
        return ownedCats;
    }

    public void setOwnedCats(Set<ServiceCat> ownedCats) {
        this.ownedCats = ownedCats;
    }

    public ServiceOwner() {
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public Date getOwner_birth_date() {
        return serviceOwner_birth_date;
    }

    @Override
    public Set<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Date getServiceOwner_birth_date() {
        return serviceOwner_birth_date;
    }

    public void setServiceOwner_birth_date(Date serviceOwner_birth_date) {
        this.serviceOwner_birth_date = serviceOwner_birth_date;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
