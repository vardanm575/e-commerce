package net.javaguides.ecommerce.controller;

import net.javaguides.ecommerce.model.User;
import net.javaguides.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostConstruct
    public void initRolesAndUsers(){
        userService.initRolesAndUser();
    }

    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }



    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin(){
        return "This url is only for Admin";
    }


    @GetMapping("/forUser")
    @PreAuthorize("hasAnyRole('Admin','User')")
    public String forUser(){
        return "This url is only for User";
    }

}
