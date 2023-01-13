package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    UserDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean saveUser(User user) {
        entityManager.merge(user);
        return true;
    }

    @Override
    public List<User> getList() {
        return entityManager.createQuery("select distinct u from User u left join fetch u.roles ", User.class)
                .getResultList();
    }

    @Override
    public User getUserId(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void deleteUser(Long id) {
        entityManager.createQuery(
                "DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
    }

    @Override
    public void editUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getEmail(String email) {
        return entityManager.createQuery("select u from User u where u.email = :id", User.class)
                .setParameter("id", email)
                .getResultList().stream().findAny().orElse(null);

    }

}