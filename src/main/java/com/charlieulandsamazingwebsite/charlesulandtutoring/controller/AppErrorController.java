/*
package com.charlieulandsamazingwebsite.charlesulandtutoring.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer status = statusCode != null ? Integer.valueOf(statusCode.toString()) : 500;

        model.addAttribute("status", status); // Optional

        return switch (status) {
            case 401 -> "error/401";
            case 403 -> "error/403";
            case 404 -> "error/404";
            case 500 -> "error/500";
            default -> "error/error";
        };
    }
}
*/
