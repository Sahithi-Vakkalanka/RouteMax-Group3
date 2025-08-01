package com.routemax.teamroutemax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.routemax.teamroutemax.dto.UserRegistrationDTO;
import com.routemax.teamroutemax.entity.User;
import com.routemax.teamroutemax.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class FormController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegistrationDTO dto, Model model, HttpSession session) {
        try {
            userService.registerUser(dto);
            User user = userService.getUserByEmail(dto.getEmail());
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        } catch (Exception e) {
            model.addAttribute("message", "Registration failed: " + e.getMessage());
            return "register";
        }
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String password,
                        Model model,
                        HttpSession session) {
        User user = userService.getUserByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            session.setAttribute("user", user);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("message", "Invalid email or password");
            return "login";
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "dashboard";
        }
        return "login";
    }

    @PostMapping("/update-name")
    public String updateName(@RequestParam String name, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            user.setName(name);
            userService.updateUser(user);
            session.setAttribute("user", user);
            model.addAttribute("user", user);
        }
        return "dashboard";
    }

    @PostMapping("/update-email")
    public String updateEmail(@RequestParam String email, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            user.setEmail(email);
            userService.updateUser(user);
            session.setAttribute("user", user);
            model.addAttribute("user", user);
        }
        return "dashboard";
    }

    @PostMapping("/update-password")
    public String updatePassword(@RequestParam String newPassword, HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            userService.updateUser(user);
            session.setAttribute("user", user);
            model.addAttribute("user", user);
        }
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
