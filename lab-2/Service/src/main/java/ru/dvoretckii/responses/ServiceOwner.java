package ru.dvoretckii.responses;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ServiceOwner {
    private Long owner_id;
    private String name;
    private Date serviceOwner_birth_date;

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
}
