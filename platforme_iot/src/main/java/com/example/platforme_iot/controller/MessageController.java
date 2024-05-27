package com.example.platforme_iot.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.platforme_iot.model.Message;
import com.example.platforme_iot.repository.MessageRepository;
import com.example.platforme_iot.repository.UserRepository;

@RestController
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/messages")
    public ResponseEntity<?> saveMessage(@RequestBody Message message) {
        try {
            // Set timestamp
            message.setTimestamp(new Date());
            
            // Check if a message with the user's ID already exists
            List<Message> existingMessages = messageRepository.findByUserId(message.getUserId());
            
            if (!existingMessages.isEmpty()) {
                // Update existing message by adding the new message to its list
                Message existing = existingMessages.get(0); // Assuming there's only one message per user for simplicity
                existing.getMessages().add(new Message.MessageDetail(message.getMessages().get(0).getMessage(), new Date(),message.getUserId()));
                messageRepository.save(existing);
            } else {
                // Save the new message
                messageRepository.save(message);
            }
            
            return ResponseEntity.ok().body("{\"message\": \"Message saved successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"Error saving message: " + e.getMessage() + "\"}");
        }
    }


    
   
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getMessagesByUserId(@RequestParam String userId) {
        try {
            List<Message> allMessages = messageRepository.findByUserId(userId);
            
         
            
            return ResponseEntity.ok(allMessages);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    @PutMapping("/messagesAdmin/{userSentId}/{userId}")
    public ResponseEntity<?> saveAdminMessage(@PathVariable String userSentId,@PathVariable String userId, @RequestBody Message message) {
        try {
            // Set timestamp
            message.setTimestamp(new Date());
            
            // Retrieve the existing user message by userId
           List<Message> existingMessageOptional = messageRepository.findByUserId(userSentId);
            
            if (!existingMessageOptional.isEmpty()) {
                // Update existing user message by adding the new admin message to its list
                Message existingMessage = existingMessageOptional.get(0);
                existingMessage.getMessages().add(new Message.MessageDetail(message.getMessages().get(0).getMessage(), new Date(),userId));
                messageRepository.save(existingMessage);
            } else {
            	  return ResponseEntity.status(HttpStatus.NOT_FOUND)
                          .body("{\"error\": \"User message not found with userId: " + userSentId + "\"}");
 
            }
            
            return ResponseEntity.ok().body("{\"message\": \"Admin message saved successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("{\"error\": \"Error saving admin message: " + e.getMessage() + "\"}");
        }
    }




    @GetMapping("/unread-messages/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getUnreadMessages(@PathVariable String userId) {
        try {
            // Retrieve all unread messages
           // List<Message> unreadMessages = messageRepository.findByIsRead(false);
            List<Message> unreadMessages = messageRepository.findByAdminOrAdminIsNull(userId);

            // Convert Message objects to a list of maps containing user name and last message time
            List<Map<String, Object>> unreadMessagesInfo = unreadMessages.stream()
                    .map((Message message) -> {
                        Map<String, Object> info = new HashMap<>();
                        
                        // Get user name from user ID
                        String userName = userRepository.findById(message.getUserId())
                                .map(user -> user.getFullName())
                                .orElse("Unknown"); // Default to "Unknown" if user not found
                                
                        info.put("userName", userName);
                        info.put("lastMessageTime", message.getMessages().get(message.getMessages().size() - 1).getTime());
                        info.put("userSentId", message.getUserId());
                        return info;
                    })
                    .collect(Collectors.toList());

            return ResponseEntity.ok(unreadMessagesInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    
    
 // Update message admin attribute
    @PutMapping("/messages/{userId}/{userSentId}")
    public ResponseEntity<?> updateMessageAdmin(@PathVariable String userId, @PathVariable String userSentId) {
        try {
            // Find the message by user ID
            List<Message> messages = messageRepository.findByUserId(userSentId);
            if (!messages.isEmpty()) {
                for (Message message : messages) {
                    // Update the admin attribute
                    message.setAdmin(userId);
                    messageRepository.save(message);
                }
                return ResponseEntity.ok().body("Admin ID saved successfully");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Message not found for user ID: " + userSentId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error updating message admin: " + e.getMessage());
        }
    }


}

