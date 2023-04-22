package ru.dvoretckii.Entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Entity
@Table(name = "owners", schema = "public")
public class Owner {
    @Id
    @Column(name = "owner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long owner_id;
    @Column(name = "owner_name")
    private String name;
    @Column(name = "owner_birth_date")
    private Date owner_birth_date;

    @Override
    public String toString() {
        return "Owner{" +
                "owner_id=" + owner_id +
                ", name='" + name + '\'' +
                ", owner_birth_date=" + owner_birth_date +
                ", ownedCats=" + ownedCats +
                '}';
    }

    @Autowired
    @OneToMany(mappedBy="owner")
    private Set<Cat> ownedCats = new HashSet<>();

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner_birth_date(Date owner_birth_date) {
        this.owner_birth_date = owner_birth_date;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public String getName() {
        return name;
    }

    public Set<Cat> getOwnedCats() {
        return ownedCats;
    }

    public void setOwnedCats(Set<Cat> ownedCats) {
        this.ownedCats = ownedCats;
    }

    public Owner() {
    }

    public Date getOwner_birth_date() {
        return owner_birth_date;
    }

}
