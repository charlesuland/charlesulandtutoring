package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringClass;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSession;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringClassService;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TutoringClassController {
    @Autowired
    private TutoringClassService tcs;
    @Autowired
    private TutoringSessionService tutoringSessionService;

    @GetMapping("/admin/make-a-class-form")
    public String makeAClassForm(Model model) {
        model.addAttribute("message", "");
        model.addAttribute("tc", new TutoringClass());
        return "make-class-form";
    }
    @PostMapping("/admin/make-class")
    public String makeClass(@ModelAttribute TutoringClass tutoringClass, Model model, RedirectAttributes redirectAttributes) {
        tcs.addTutoringClass(tutoringClass);

        redirectAttributes.addFlashAttribute("tc", tutoringClass.getId());
        List<TutoringSession> sessions = tutoringClass.createSessions();
        for(TutoringSession session : sessions) {
            tutoringSessionService.save(session);
        }
        return "redirect:/admin/declare-sessions?tc="+ tutoringClass.getId();
    }

}
