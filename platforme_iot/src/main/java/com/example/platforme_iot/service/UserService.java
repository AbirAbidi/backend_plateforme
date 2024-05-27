package com.example.platforme_iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.platforme_iot.model.User;
import com.example.platforme_iot.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Service
public class UserService {
	
	public boolean validateEmail(String email) {
	        try {
	            InternetAddress internetAddress = new InternetAddress(email);
	            internetAddress.validate();
	            return true;
	        } catch (AddressException e) {
	            return false;
	        }
	    }	

    @Autowired
    private UserRepository userRepository;

    public User signUp(User user) {
        // 1. Perform Validation
        if (user.getFullName() == null || user.getFullName().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (user.getPhoneNumber() == null || user.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Phone Number cannot be empty");
        }

        // 3. Set Default Role
        user.setRole("user");


        // 4. Save User to Database
        return userRepository.save(user);
    }
    
    public boolean isValidUser(String email, String password) {
        // Implement logic to validate user credentials
        User user = userRepository.findByEmail(email);
        return user != null && user.getPassword().equals(password);
    }
    
    
    
    public String getUserRoleByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getRole();
        }
        return null; // Or handle the case where the user is not found
    }
    
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    
 // Method to update user attributes
    public User updateUserAttributes(String email, String fullName, String phoneNumber, String newEmail) {
        // Retrieve the user from the database based on the provided email
        User user = userRepository.findByEmail(email);
        
        // Check if the user exists
        if (user != null) {
            // Update user attributes with the provided values
            user.setFullName(fullName);
            user.setPhoneNumber(phoneNumber);
            user.setEmail(newEmail);
            
            // Save the updated user to the database
            return userRepository.save(user);
        } else {
            // If user does not exist, return null or throw an exception
            return null;
        }
    }
}
