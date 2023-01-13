package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;


@Service
public class UserServiceImp implements UserService {
    private final RolesService rolesService;
    private final UserDao userDao;


    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(7);
    }

    @Autowired
    public UserServiceImp(RolesService rolesService, UserDao userDao) {
        this.rolesService = rolesService;
        this.userDao = userDao;

    }

    @Override
    @Transactional
    public boolean saveUser(User user) {
        User userSet = userDao.getEmail(user.getEmail());
        if (userSet != null) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));

        userDao.saveUser(user);
        return true;
    }

    @Override
    @Transactional
    public void editUser(User user) {
        User userSet = getUserId(user.getId());
        if (!userSet.getPassword().equals(user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder().encode(user.getPassword()));
        }

        userDao.editUser(user);

    }

    @Override
    @Transactional
    public List<User> getList() {
        return userDao.getList();
    }

    @Override
    @Transactional
    public User getUserId(Long id) {
        return userDao.getUserId(id);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public User getEmail(String email) {
        return userDao.getEmail(email);
    }


}



