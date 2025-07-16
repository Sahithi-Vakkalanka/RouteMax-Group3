package com.routemax.teamroutemax.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.routemax.teamroutemax.dto.UserRegistrationDTO;
import com.routemax.teamroutemax.service.UserService;

@Controller
public class FormController {

    @Autowired
    private UserService userService;

    // Show the HTML form in browser
    // @GetMapping("/register")
    // public String showForm(Model model) {
    //     model.addAttribute("user", new UserRegistrationDTO());
    //     return "register"; // loads register.jsp
    // }

    // Handle form submit from HTML
    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegistrationDTO dto, Model model) {
        try {
            userService.registerUser(dto);
            model.addAttribute("message", "User registered successfully!");
        } catch (Exception e) {
            model.addAttribute("message", "Registration failed: " + e.getMessage());
        }
        return "register";
    }
}
