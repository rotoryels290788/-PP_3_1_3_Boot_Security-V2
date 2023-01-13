package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.Set;

@Entity

@Table(name = "roles")
public class Roles implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long id;
    @Column(name = "role")
    private String name;
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public Roles(String name) {
        this.name = name;
    }

    public Roles() {

    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }
}

