package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.User;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/add-user")
    public String addUser(@ModelAttribute User user, Model model) {
        model.addAttribute("user1", user);
        userService.addUser(user);
        return "user-added";
    }

    @GetMapping("/user-form")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }
    @GetMapping("/all_users")
    public String allUsers(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "all-users";
    }




}
