package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @Autowired
    private EmailService emailService;



    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("pageTitle", "Charles Uland Tutoring");
        model.addAttribute("view", "home");
        model.addAttribute("pageStyles", "/css/home.css");
        return "layout";
    }


}
