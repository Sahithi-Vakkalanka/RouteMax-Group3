package com.routemax.teamroutemax.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.routemax.teamroutemax.entity.User;
import com.routemax.teamroutemax.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    // Show admin dashboard with all users (for JSP/Thymeleaf)
    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin-dashboard"; // admin-dashboard.jsp or .html
    }

    // REST API: Get all users (for AJAX)
    @GetMapping("/users")
    @ResponseBody
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // REST API: Delete user (for AJAX)
    @DeleteMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteUserApi(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("User deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    // REST API: Update user (for AJAX)
    @PutMapping("/users/{id}")
    @ResponseBody
    public ResponseEntity<String> updateUserApi(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isPresent()) {
            User existingUser = userOpt.get();
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            userService.saveUser(existingUser);
            return ResponseEntity.ok("User updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }
    }

    // FORM: Delete user (for HTML form POST)
    @PostMapping("/delete/{id}")
    public String deleteUserForm(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/dashboard";
    }

    // FORM: Edit user (for HTML form POST)
    @PostMapping("/edit/{id}")
    public String editUserForm(@PathVariable Long id, @ModelAttribute User user) {
        user.setId(id);
        userService.saveUser(user);
        return "redirect:/admin/dashboard";
    }

    // Show edit form (for HTML form)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<User> userOpt = userService.getUserById(id);
        if (userOpt.isPresent()) {
            model.addAttribute("user", userOpt.get());
            return "edit-user"; // edit-user.jsp or .html
        } else {
            return "redirect:/admin/dashboard";
        }
    }
}