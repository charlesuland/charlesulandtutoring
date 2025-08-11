package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.exception.AppUserAlreadyExistsException;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.AppUser;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.UserRegistrationDto;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/register-an-account")
    public String userForm(Model model) {
        model.addAttribute("user", new UserRegistrationDto());
        return "register-an-account";
    }

    @PostMapping("/register-an-account")
    public String addUser(@ModelAttribute("user") @Valid UserRegistrationDto userDto, BindingResult result, Model model, HttpServletRequest request) {

        if(result.hasErrors()) {
            return "register-an-account";
        }

        if(!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            model.addAttribute("passwordError", "Passwords do not match.");
            return "register-an-account";
        }

        try {
            userService.registerNewUser(userDto);

            request.login(userDto.getEmail(), userDto.getPassword());
            return "redirect:/";
        } catch (AppUserAlreadyExistsException e) {
            model.addAttribute("emailError", "There is an account already associated with this email.");
            return "register-an-account";
        } catch (ServletException e) {
            model.addAttribute("loginError", "Login after registration failed. Try logging in normally.");
            return "register-an-account";
        }

    }






}
