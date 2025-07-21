package com.routemax.teamroutemax.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminPageController {

    @GetMapping("/admin")
    public String showAdminPage() {
        return "admin";  // This maps to /WEB-INF/views/admin.jsp
    }
}

