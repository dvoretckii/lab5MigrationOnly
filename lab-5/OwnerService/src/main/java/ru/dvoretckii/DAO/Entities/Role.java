package ru.dvoretckii.DAO.Entities;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;


import java.util.Set;

@Entity
@Table(name = "dic_role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;

    @Column(name = "role")
    private String name;

    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<Owner> users;
    public Role() {
    }

    public Role(Long id) {
        this.id = id;
        if (id == 2) {
            this.name = "ROLE_USER";
        }
        else {
            this.name = "ROLE_ADMIN";
        }
    }

    public Set<Owner> getUsers() {
        return users;
    }

    public void setUsers(Set<Owner> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long roleId) {
        this.id = roleId;
    }

    public Long getId() {
        return id;
    }
}
