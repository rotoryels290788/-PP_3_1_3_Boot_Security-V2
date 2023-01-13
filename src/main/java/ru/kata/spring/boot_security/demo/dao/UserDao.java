package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserDao {
    boolean saveUser(User user);
    User getEmail(String email);
    List<User> getList();

    User getUserId(Long id);

    void deleteUser(Long id);

    void editUser(User user);



}