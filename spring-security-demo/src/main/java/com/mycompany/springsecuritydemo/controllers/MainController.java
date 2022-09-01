package com.mycompany.springsecuritydemo.controllers;

import com.mycompany.springsecuritydemo.entities.User;
import com.mycompany.springsecuritydemo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        return "this page is visible only for authenticated users";
    }

    @GetMapping("/admins")
    public String adminsPage(Principal principal){
        User user = userService.findByUsername(principal.getName());
        return "Hello, admin: " +  user.getUsername() + ", email: " + user.getEmail();
    }
}
