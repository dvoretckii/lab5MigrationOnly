package ru.dvoretckii.responses;

import ru.dvoretckii.BusinessCat;
import ru.dvoretckii.Entities.Cat;
import ru.dvoretckii.Entities.Color;
import ru.dvoretckii.Repositories.CatRepository;

import java.awt.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ServiceCat {

    private Long serviceCat_id;

    private String serviceCat_name;
    private ru.dvoretckii.Entities.Color color;

    private String serviceCat_breed;
    private Set<ServiceCat> friends = new HashSet<>();
    private ServiceOwner serviceOwner;

    public Long getServiceCat_id() {
        return serviceCat_id;
    }

    public void setServiceOwner(ServiceOwner serviceOwner) {
        this.serviceOwner = serviceOwner;
    }

    public Date getServiceCat_birth_date() {
        return serviceCat_birth_date;
    }

    private Date serviceCat_birth_date;
    public ServiceCat() {

    }

    public String getServiceCat_name() {
        return serviceCat_name;
    }

    @Override
    public String toString() {
        return "ServiceCat{" +
                "serviceCat_id=" + serviceCat_id +
                ", serviceCat_name='" + serviceCat_name + '\'' +
                ", color=" + color +
                ", serviceCat_breed='" + serviceCat_breed + '\'' +
                ", serviceOwner=" + serviceOwner +
                ", serviceCat_birth_date=" + serviceCat_birth_date +
                '}';
    }

    public Color getColor() {
        return color;
    }

    public String getServiceCat_breed() {
        return serviceCat_breed;
    }

    public Set<ServiceCat> getFriends() {
        return friends;
    }

    public void setServiceCat_id(Long ServiceCat_id) {
        this.serviceCat_id = ServiceCat_id;
    }

    public void setServiceCat_name(String ServiceCat_name) {
        this.serviceCat_name = ServiceCat_name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setServiceCat_breed(String ServiceCat_breed) {
        this.serviceCat_breed = ServiceCat_breed;
    }

    public void setServiceCat_birth_date(Date ServiceCat_birth_date) {
        this.serviceCat_birth_date = ServiceCat_birth_date;
    }

    public void setFriends(Set<ServiceCat> friends) {
        this.friends = friends;
    }

    public ServiceOwner getServiceOwner() {
        return serviceOwner;
    }

}
