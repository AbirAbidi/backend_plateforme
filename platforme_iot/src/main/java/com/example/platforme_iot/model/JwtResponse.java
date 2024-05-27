package com.example.platforme_iot.model;

public class JwtResponse {

    private String token;
    private String message;
    private String role;

    public JwtResponse(String token, String message, String role) {
        this.token = token;
        this.message = message;
        this.role = role;
        
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
