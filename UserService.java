package com.routemax.teamroutemax.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.routemax.teamroutemax.dto.UserRegistrationDTO;
import com.routemax.teamroutemax.entity.User;
import com.routemax.teamroutemax.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(UserRegistrationDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword())); // hashed password

        userRepository.save(user);
        return "User registered successfully!";
    }

    // ✅ NEW: Return all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // ✅ NEW: Delete user by ID
    public boolean deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
    // add inside UserService class

public User getUserByEmail(String email) {
    return userRepository.findByEmail(email).orElse(null);
}
public Optional<User> getUserById(Long id) {
    return userRepository.findById(id);
}

public void updateUser(User user) {
    userRepository.save(user);
}
public void saveUser(User user) {
    userRepository.save(user);  // ✅ this saves to DB
}
public void registerRawUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
}




}

