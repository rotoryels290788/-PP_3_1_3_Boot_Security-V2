package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService {


    boolean saveUser(User user);

    List<User> getList();

    User getUserId(Long id);

    void deleteUser(Long id);

    void editUser(User user);

    User getEmail(String email);

}