package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {
    @Autowired
    private TutoringClassService tcs;


    //get the text formatting straight from back end entirely
    @GetMapping("/public/classes")
    public String classes(Model model) {

        model.addAttribute("pageTitle", "Classes");
        model.addAttribute("view", "classes");
        model.addAttribute("pageStyles", "/css/classes.css");

        model.addAttribute("thisMonthsClasses", tcs.getThisMonthsClasses());
        return "layout";
    }
}
