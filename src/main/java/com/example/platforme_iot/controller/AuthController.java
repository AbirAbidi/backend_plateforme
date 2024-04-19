package com.example.platforme_iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.platforme_iot.model.JwtResponse;
import com.example.platforme_iot.model.SignInRequest;
import com.example.platforme_iot.service.UserService;
import com.example.platforme_iot.util.JwtTokenUtil;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest) {
        // Validate username and password
        if (!userService.isValidUser(signInRequest.getEmail(), signInRequest.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        
        // Fetch user's role
        String role = userService.getUserRoleByEmail(signInRequest.getEmail());

        // Generate JWT token
        String token = jwtTokenUtil.generateToken(signInRequest.getEmail());

        String message = "User signed in successfully: ";

        // Return token to the client
        return ResponseEntity.ok(new JwtResponse(token,message,role));
    }
    
    
    
}
