package net.javaguides.ecommerce.service;

import net.javaguides.ecommerce.dao.RoleDao;
import net.javaguides.ecommerce.dao.UserDao;
import net.javaguides.ecommerce.model.Role;
import net.javaguides.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public User registerNewUser(User user){
        Role role = roleDao.findById("User").get();
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRoles(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public void initRolesAndUser(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRoles(adminRoles);
        userDao.save(adminUser);


        User userUser = new User();
        userUser.setUserName("raj123");
        userUser.setUserPassword(getEncodedPassword("raj@pass"));
        userUser.setUserFirstName("raj");
        userUser.setUserLastName("raj");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        userUser.setRoles(userRoles);
        userDao.save(userUser);
    }

    public String getEncodedPassword(String password){
        return passwordEncoder.encode(password);
    }
}
