package net.javaguides.ecommerce.service;

import net.javaguides.ecommerce.dao.RoleDao;
import net.javaguides.ecommerce.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role){
        return roleDao.save(role);
    }
}
