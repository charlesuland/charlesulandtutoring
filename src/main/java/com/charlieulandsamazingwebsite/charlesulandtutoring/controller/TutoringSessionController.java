package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSession;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.TutoringSessionListWrapper;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringClassService;
import com.charlieulandsamazingwebsite.charlesulandtutoring.service.TutoringSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TutoringSessionController {

    @Autowired
    private TutoringSessionService tss;
    @Autowired
    private TutoringClassService tutoringClassService;

    @GetMapping("/admin/declare-sessions")
    public String declareSessions(@RequestParam("tc") int tcId, Model model) {

        List<TutoringSession> sessions = tss.getSessionsFromClass(tutoringClassService.findById(tcId));

        model.addAttribute("sessionWrapper", new TutoringSessionListWrapper(sessions)); // Add the wrapper object
        return "declare-sessions";
    }


    @PostMapping("/admin/submit-sessions")
    public String submitSessions(@ModelAttribute TutoringSessionListWrapper sessionWrapper, Model model) {
        List<TutoringSession> submittedSessions = sessionWrapper.getSessions();

        for (TutoringSession submittedSession : submittedSessions) {


            TutoringSession existingSession = tss.findById(submittedSession.getId());


            existingSession.setTime(submittedSession.getTime());
            existingSession.setDescription(submittedSession.getDescription());
            existingSession.setZoomLink(submittedSession.getZoomLink());


            tss.save(existingSession);
        }


        return "redirect:/home";
    }

}
