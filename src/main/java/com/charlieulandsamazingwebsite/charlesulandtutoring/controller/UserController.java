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
    public void addUser(@ModelAttribute User user) {

    }

    @GetMapping("/user-form")
    public String userForm(Model model) {
        model.addAttribute("user", new User());
        return "form";
    }
}
