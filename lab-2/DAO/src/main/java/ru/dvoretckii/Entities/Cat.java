package ru.dvoretckii.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cats", schema = "public")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long cat_id;
    @Column(name = "cat_name")
    private String cat_name;
    @Enumerated(EnumType.STRING)
    @Column(name = "cat_color")
    private Color color;

    @Column(name = "cat_breed")
    private String cat_breed;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "cats_relationships",
            joinColumns = { @JoinColumn(name = "cat_id") },
            inverseJoinColumns = { @JoinColumn(name = "cat_friend_id") }
    )
    private Set<Cat> friends = new HashSet<>();
    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name="owner_id", nullable = true)
    private Owner owner;
    @Column(name = "cat_birth_date")
    private Date cat_birth_date;
    public Cat() {

    }

    public String getCat_name() {
        return cat_name;
    }

    public Color getColor() {
        return color;
    }

    public String getCat_breed() {
        return cat_breed;
    }

    public Set<Cat> getFriends() {
        return friends;
    }

    public void setCat_id(Long cat_id) {
        this.cat_id = cat_id;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setCat_breed(String cat_breed) {
        this.cat_breed = cat_breed;
    }

    public void setCat_birth_date(Date cat_birth_date) {
        this.cat_birth_date = cat_birth_date;
    }

    public void setFriends(Set<Cat> friends) {
        this.friends = friends;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Date getCat_birth_date() {
        return cat_birth_date;
    }

}
