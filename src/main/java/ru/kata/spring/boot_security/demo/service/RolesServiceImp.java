package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RolesDao;
import ru.kata.spring.boot_security.demo.model.Roles;
import java.util.List;

@Service
public class RolesServiceImp implements RolesService {

    private final RolesDao rolesDao;

    @Autowired
    public RolesServiceImp(RolesDao rolesDao) {
        this.rolesDao = rolesDao;
    }


    @Override
    @Transactional
    public boolean saveRole(Roles role) {
        Roles roleSet = rolesDao.findByName(role.getName());
        if(roleSet !=null) {
            return false;

        }
      return rolesDao.saveRole(role);

    }

    @Override
    @Transactional
    public Roles findByName(String name) {
        return rolesDao.findByName(name);
    }

    @Override
    @Transactional
    public List<Roles> getList() {
        return rolesDao.getList();
    }

    @Override
    @Transactional
    public Roles getRoleId(Long id) {
        return rolesDao.getRole(id);
    }

    @Override
    @Transactional
   public List<Roles> listByName(List<String> name) {
        return rolesDao.listByName(name);
    }

}
