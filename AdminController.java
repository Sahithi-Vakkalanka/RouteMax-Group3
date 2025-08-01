// package com.routemax.teamroutemax.controller;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.routemax.teamroutemax.entity.User;
// import com.routemax.teamroutemax.service.UserService;

// @RestController
// @RequestMapping("/admin")
// public class AdminController {

//     private final UserService userService;

//     public AdminController(UserService userService) {
//         this.userService = userService;
//     }

//     // ✅ Get all users
//     @GetMapping("/users")
//     public List<User> getAllUsers() {
//         return userService.getAllUsers();
//     }

//     // ✅ Delete user
//     @DeleteMapping("/users/{id}")
//     public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//         boolean deleted = userService.deleteUser(id);
//         if (deleted) {
//             return ResponseEntity.ok("User deleted successfully.");
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//         }
//     }

//     // ✅ Update user (EDIT)
//     @PutMapping("/users/{id}")
//     public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//         Optional<User> userOpt = userService.getUserById(id);
//         if (userOpt.isPresent()) {
//             User existingUser = userOpt.get();
//             existingUser.setName(updatedUser.getName());
//             existingUser.setEmail(updatedUser.getEmail());
//             userService.saveUser(existingUser);
//             return ResponseEntity.ok("User updated successfully.");
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//         }
//     }
// }
// package com.routemax.teamroutemax.controller;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.routemax.teamroutemax.entity.User;
// import com.routemax.teamroutemax.service.UserService;

// @RestController
// @RequestMapping("/admin")
// public class AdminController {

//     private final UserService userService;

//     public AdminController(UserService userService) {
//         this.userService = userService;
//     }

//     @GetMapping("/users")
//     public List<User> getAllUsers() {
//         return userService.getAllUsers();
//     }

//     @DeleteMapping("/users/{id}")
//     public ResponseEntity<String> deleteUser(@PathVariable Long id) {
//         boolean deleted = userService.deleteUser(id);
//         if (deleted) {
//             return ResponseEntity.ok("User deleted successfully.");
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//         }
//     }


//     @PutMapping("/users/{id}")
//     public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//         Optional<User> userOpt = userService.getUserById(id);
//         if (userOpt.isPresent()) {
//             User existingUser = userOpt.get();
//             existingUser.setName(updatedUser.getName());
//             existingUser.setEmail(updatedUser.getEmail());
//             userService.saveUser(existingUser);
//             return ResponseEntity.ok("User updated successfully.");
//         } else {
//             return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//         }
//     }

//     @PostMapping("/users")
// public ResponseEntity<String> addUser(@RequestBody User user) {
//     if (user.getName() == null || user.getEmail() == null || user.getPassword() == null) {
//         return ResponseEntity.badRequest().body("Missing fields");
//     }

//     if (userService.getUserByEmail(user.getEmail()) != null) {
//         return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
//     }

//     userService.registerRawUser(user);  // NEW METHOD (see below)
//     return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully.");
// }

// @GetMapping("/admin/users/{id}")
// @ResponseBody
// public ResponseEntity<User> getUserById(@PathVariable Long id) {
//     Optional<User> user = UserRepository.findById(id);
//     return user.map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
// }



// }
// package com.routemax.teamroutemax.controller;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.routemax.teamroutemax.entity.User;
// import com.routemax.teamroutemax.repository.UserRepository;


// //@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
// @CrossOrigin(origins = "http://localhost:8080") 
// @RestController
// @RequestMapping("/admin")





// public class AdminController {

//     @Autowired
//     private UserRepository userRepository;

//     // ✅ Get all users
//     @GetMapping("/users")
//     public List<User> getAllUsers() {
//         return userRepository.findAll();
//     }

//     // ✅ Get user by ID
// @GetMapping("/users/{id}")
// public ResponseEntity<Object> getUserById(@PathVariable Long id) {
//     Optional<User> user = userRepository.findById(id);
//     return user.<ResponseEntity<Object>>map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.status(404).body("User not found"));
// }



//     // ✅ Delete user by ID
//     @DeleteMapping("/users/{id}")
//     public ResponseEntity<?> deleteUser(@PathVariable Long id) {
//         if (userRepository.existsById(id)) {
//             userRepository.deleteById(id);
//             return ResponseEntity.ok("User deleted successfully");
//         } else {
//             return ResponseEntity.status(404).body("User not found");
//         }
//     }

//     // ✅ Update user by ID
//     @PutMapping("/users/{id}")
//     public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
//         Optional<User> optionalUser = userRepository.findById(id);
//         if (optionalUser.isPresent()) {
//             User user = optionalUser.get();
//             user.setName(updatedUser.getName());
//             user.setEmail(updatedUser.getEmail());
//             return ResponseEntity.ok(userRepository.save(user));
//         } else {
//             return ResponseEntity.status(404).body("User not found");
//         }
//     }

//     // ✅ Create a new user
//     @PostMapping("/users")
//     public ResponseEntity<?> createUser(@RequestBody User user) {
//         if (userRepository.existsByEmail(user.getEmail())) {
//             return ResponseEntity.badRequest().body("Email already exists");
//         }
//         return ResponseEntity.ok(userRepository.save(user));
//     }
// }

package com.routemax.teamroutemax.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.routemax.teamroutemax.entity.User;
import com.routemax.teamroutemax.repository.UserRepository;

@CrossOrigin(origins = "*")  // ❗ KEEP THIS HERE FOR NOW
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) return "User not found";
        userRepository.deleteById(id);
        return "Deleted successfully";
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            return userRepository.save(user);
        }
        return null;
    }

//     @PostMapping("/users")
//     public User createUser(@RequestBody User user) {
//         return userRepository.save(user);
//     }

//     @PostMapping("/users/{id}")
// public ResponseEntity<?> overrideMethod(
//         @PathVariable Long id,
//         @RequestBody User user,
//         @RequestParam("method") String method) {

//     if (method.equalsIgnoreCase("put")) {
//         return userRepository.findById(id)
//             .map(existingUser -> {
//                 existingUser.setName(user.getName());
//                 existingUser.setEmail(user.getEmail());
//                 return ResponseEntity.ok(userRepository.save(existingUser));
//             }).orElse(ResponseEntity.notFound().build());
//     }

//     if (method.equalsIgnoreCase("delete")) {
//         userRepository.deleteById(id);
//         return ResponseEntity.ok().build();
//     }

//     return ResponseEntity.badRequest().body("Unsupported method");
// }
@PostMapping("/users/update/{id}")
public ResponseEntity<?> updateUserViaPost(@PathVariable Long id, @RequestBody User updatedUser) {
    return userRepository.findById(id)
            .map(user -> {
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return ResponseEntity.ok(userRepository.save(user));
            }).orElse(ResponseEntity.notFound().build());
}

@PostMapping("/users/delete/{id}")
public ResponseEntity<?> deleteUserViaPost(@PathVariable Long id) {
    if (userRepository.existsById(id)) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    } else {
        return ResponseEntity.notFound().build();
    }
}


}
