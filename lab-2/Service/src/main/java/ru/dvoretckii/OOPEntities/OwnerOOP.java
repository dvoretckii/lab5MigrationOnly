package ru.dvoretckii.OOPEntities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class OwnerOOP {
    private Long owner_id;
    private String name;
    private Date serviceOwner_birth_date;
    private Set<CatOOP> ownedCats = new HashSet<>();

    public Long getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(Long owner_id) {
        this.owner_id = owner_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getServiceOwner_birth_date() {
        return serviceOwner_birth_date;
    }

    public void setServiceOwner_birth_date(Date serviceOwner_birth_date) {
        this.serviceOwner_birth_date = serviceOwner_birth_date;
    }

    public Set<CatOOP> getOwnedCats() {
        return ownedCats;
    }

    public void setOwnedCats(Set<CatOOP> ownedCats) {
        this.ownedCats = ownedCats;
    }

    @Override
    public String toString() {
        return "OwnerOOP{" +
                "owner_id=" + owner_id +
                ", name='" + name + '\'' +
                ", serviceOwner_birth_date=" + serviceOwner_birth_date +
                ", ownedCats=" + ownedCats +
                '}';
    }
}
