package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Roles;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RolesDaoImp implements RolesDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean  saveRole(Roles roles) {
        entityManager.persist(roles);
        return true;
    }

    @Override
    public Roles getRole(Long id) {
        return entityManager.find(Roles.class, id);
    }
    @Override
    public List<Roles> getList() {
        return entityManager.createQuery("select r from Roles r", Roles.class).getResultList();
    }

    @Override
    public List<Roles> listByName(List<String> name) {
        return entityManager.createQuery("select r FROM Roles r WHERe r.name in (:id)", Roles.class)
                .setParameter("id", name)
                .getResultList();
    }

    @Override
    public Roles findByName(String name) {
        return entityManager.createQuery("select r FROM Roles r WHERe r.name = :id", Roles.class)
                .setParameter("id", name)
                .getResultList().stream().findAny().orElse(null);
    }


}
