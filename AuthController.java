// package com.routemax.teamroutemax.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.routemax.teamroutemax.dto.UserRegistrationDTO;
// import com.routemax.teamroutemax.service.UserService;

// @RestController
// @RequestMapping("/api/auth")
// public class AuthController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/register")
//     public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDTO dto) {
//         String result = userService.registerUser(dto);
//         return ResponseEntity.ok(result);



//     }
// }
package com.routemax.teamroutemax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.routemax.teamroutemax.dto.UserRegistrationDTO;
import com.routemax.teamroutemax.service.UserService;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // Show the form when user visits /register
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegistrationDTO());
        return "register"; // register.jsp
    }

    // Handle the form submission
    @PostMapping("/api/auth/register")
    @ResponseBody
    public String registerUser(@ModelAttribute("user") UserRegistrationDTO dto) {
        userService.registerUser(dto);
        return "User registered successfully!";
    }
}

