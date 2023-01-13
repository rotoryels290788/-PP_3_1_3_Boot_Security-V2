package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Roles;
import java.util.List;

public interface RolesService {

    boolean saveRole (Roles role);

    Roles findByName(String name);

    List<Roles> getList();

    Roles getRoleId(Long id);

    List<Roles> listByName(List<String> name);

  

}