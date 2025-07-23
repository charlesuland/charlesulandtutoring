package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MeetingsController {

    @GetMapping("/my-meetings")
    public String myMeetings(Model model) {
        //need to figure out how to get user name;
        //model.addAttribute();
        return "my-meetings";
    }
}
