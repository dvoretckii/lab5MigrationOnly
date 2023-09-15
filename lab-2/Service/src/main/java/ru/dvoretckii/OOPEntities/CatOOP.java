package ru.dvoretckii.OOPEntities;

import ru.dvoretckii.Entities.Color;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CatOOP {
    private Long serviceCat_id;

    private String serviceCat_name;
    private ru.dvoretckii.Entities.Color color;

    private String serviceCat_breed;
    private Set<CatOOP> friends = new HashSet<>();
    private OwnerOOP ownerOOP;
    private Date cat_birth_date;

    public Long getServiceCat_id() {
        return serviceCat_id;
    }

    public void setServiceCat_id(Long serviceCat_id) {
        this.serviceCat_id = serviceCat_id;
    }

    public String getServiceCat_name() {
        return serviceCat_name;
    }

    public void setServiceCat_name(String serviceCat_name) {
        this.serviceCat_name = serviceCat_name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getServiceCat_breed() {
        return serviceCat_breed;
    }

    public void setServiceCat_breed(String serviceCat_breed) {
        this.serviceCat_breed = serviceCat_breed;
    }

    public Set<CatOOP> getFriends() {
        return friends;
    }

    public void setFriends(Set<CatOOP> friends) {
        this.friends = friends;
    }

    public OwnerOOP getOwnerOOP() {
        return ownerOOP;
    }

    public void setOwnerOOP(OwnerOOP ownerOOP) {
        this.ownerOOP = ownerOOP;
    }

    public CatOOP() {

    }

    public Date getCat_birth_date() {
        return cat_birth_date;
    }

    public void setCat_birth_date(Date cat_birth_date) {
        this.cat_birth_date = cat_birth_date;
    }

    @Override
    public String toString() {
        return "CatOOP{" +
                "serviceCat_id=" + serviceCat_id +
                ", serviceCat_name='" + serviceCat_name + '\'' +
                ", color=" + color +
                ", serviceCat_breed='" + serviceCat_breed + '\'' +
                ", friends=" + friends +
                ", ownerOOP=" + ownerOOP +
                ", cat_birth_date=" + cat_birth_date +
                '}';
    }
}
