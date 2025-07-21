package com.routemax.teamroutemax.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showHome() {
        return "home";  // Spring will look for /WEB-INF/views/home.jsp
    }
}

