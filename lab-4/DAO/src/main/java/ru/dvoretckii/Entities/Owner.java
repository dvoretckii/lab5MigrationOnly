package ru.dvoretckii.Entities;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "owners", schema = "public")
public class Owner implements UserDetails {
    @Id
    @Column(name = "owner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long owner_id;
    @Column(name = "username")
    private String username;
    @Column(name = "owner_birth_date")
    private Date owner_birth_date;
    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

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
        return username;
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

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "owner_id=" + owner_id +
                ", name='" + username + '\'' +
                ", owner_birth_date=" + owner_birth_date +
                ", ownedCats=" + ownedCats +
                '}';
    }

    @Autowired
    @OneToMany(mappedBy="owner")
    private Set<Cat> ownedCats = new HashSet<>();

    public void setName(String name) {
        this.username = name;
    }

    public void setOwner_birth_date(Date owner_birth_date) {
        this.owner_birth_date = owner_birth_date;
    }

    public Long getOwner_id() {
        return owner_id;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
