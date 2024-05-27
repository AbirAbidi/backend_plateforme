package com.example.platforme_iot.controller;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.platforme_iot.model.User;
import com.example.platforme_iot.repository.UserRepository;
import com.example.platforme_iot.service.UserService;

@RestController
@RequestMapping("/api/users") // Base path for user-related endpoints
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody User user) {
        try {
        	
            // Call signUp method to save the user
            User savedUser = userService.signUp(user);
            return ResponseEntity.ok(savedUser);
        } catch (IllegalArgumentException e) {
            // Handle validation errors
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Handle other errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
     
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        try {
            List<User> users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            // Handle errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    // get user Id by his email
    @GetMapping("/id")
    public ResponseEntity<?> getUserIdByEmail(@RequestParam String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(Collections.singletonMap("id", user.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }	
    
    @PutMapping("/update-name/{userId}")
    public ResponseEntity<?> updateName(@PathVariable String userId, @RequestBody String fullName) {
        // Find user by ID
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // Update user's name
            user.setFullName(fullName);
            userRepository.save(user);
            return ResponseEntity.ok().body("{\"message\": \"Name updated successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/update-phone/{userId}")
    public ResponseEntity<?> updatePhoneNumber(@PathVariable  String userId, @RequestBody String phoneNumber) {
        // Find user by ID
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // Update user's phone number
            user.setPhoneNumber(phoneNumber);
            userRepository.save(user);
            return ResponseEntity.ok().body("{\"message\": \"Phone number updated successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping("/update-email/{userId}")
    public ResponseEntity<?> updateEmail(@PathVariable String userId, @RequestBody String newEmail) {
        // Find user by ID
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // Update user's email
            user.setEmail(newEmail);
            userRepository.save(user);
            return ResponseEntity.ok().body("{\"message\": \"Email updated successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    
    
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        // Find user by ID
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            // Delete the user
            userRepository.delete(user);
            // Return a JSON response with a success message
            return ResponseEntity.ok(Collections.singletonMap("message", "User deleted successfully"));
        } else {
            // Return a JSON response with a not found message
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "User not found"));
        }
    }
    
    
    @PutMapping("/update-role/{email}")
    public ResponseEntity<?> updateRole(@PathVariable String email, @RequestParam String newRole) {
        // Find user by email
        User user = userRepository.findByEmail(email);
        if (user != null) {
            // Update user's role
            user.setRole(newRole);
            userRepository.save(user);
            return ResponseEntity.ok().body("{\"message\": \"Role updated successfully\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
