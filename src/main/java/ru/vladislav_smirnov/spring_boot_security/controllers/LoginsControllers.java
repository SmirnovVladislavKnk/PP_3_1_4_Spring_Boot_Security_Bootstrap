package ru.vladislav_smirnov.spring_boot_security.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginsControllers {

    @GetMapping("/login")
    public String login() {
        return "/login/index";
    }
}
