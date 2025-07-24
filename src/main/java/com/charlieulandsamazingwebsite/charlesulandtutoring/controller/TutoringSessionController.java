package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSession;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TutoringSessionController {
    @Autowired
    private TutoringSessionService tss;

    @GetMapping("/make-a-class-form")
    public String makeAClassForm(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("ts", new TutoringSession());
        return "make-class-form";
    }
    @PostMapping("/make-class")
    public String makeClass(@ModelAttribute TutoringSession tutoringSession, Model model) {
        tss.addTutoringSession(tutoringSession);
        model.addAttribute("ts", new TutoringSession());
        model.addAttribute("message", "Class created");
        return "make-class-form";
    }
    @GetMapping("/all-classes")
    public String allClasses(Model model) {
        model.addAttribute("meetings", tss.allMeetings());
        return "all-classes";
    }

}
