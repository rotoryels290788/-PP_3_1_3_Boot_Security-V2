package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.Roles;
import java.util.List;

public interface RolesDao {
    boolean saveRole(Roles roles);

    Roles getRole(Long id);

    List<Roles> getList();

    Roles findByName(String name);

    List<Roles> listByName(List<String> name);


}