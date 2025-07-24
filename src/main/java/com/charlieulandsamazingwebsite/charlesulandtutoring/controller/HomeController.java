package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index() {
        return "index"; // returns index.html or index.html in templates
    }
}
