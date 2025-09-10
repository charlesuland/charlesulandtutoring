package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

@Controller
public class PublicController {
    @Autowired
    private TutoringClassService tcs;


    //get the text formatting straight from back end entirely
    @GetMapping("/classes")
    public String classes(Model model) {

        model.addAttribute("pageTitle", "Classes");
        model.addAttribute("view", "classes");
        model.addAttribute("pageStyles", "/css/classes.css");

        String month = LocalDate.now().getMonth().toString().toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);

        model.addAttribute("thisMonth", month);
        model.addAttribute("thisMonthsClasses", tcs.getThisMonthsClasses());
        return "layout";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "About");
        model.addAttribute("view", "about");
        model.addAttribute("pageStyles", "/css/about.css");

        return "layout";

    }
    @GetMapping("/faqs")
    public String faqs(Model model) {
        model.addAttribute("pageTitle", "FAQs");
        model.addAttribute("view", "faqs");
        model.addAttribute("pageStyles", "/css/faqs.css");

        return "layout";

    }
    @GetMapping("/study-resources")
    public String studyResources(Model model) {
        //currently coming soon
        model.addAttribute("pageTitle", "Study Resources");
        model.addAttribute("view", "coming-soon");
        model.addAttribute("pageStyles", "/css/comingSoon.css");

        return "layout";

    }
}
