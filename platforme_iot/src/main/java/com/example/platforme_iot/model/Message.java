package com.example.platforme_iot.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "messages")
public class Message {
    @Id
    private String id;
    private String userId;
    private boolean isRead;
    private String admin ;
    private List<MessageDetail> messages; // User messages
    private Date timestamp;

    public Message() {
        this.messages = new ArrayList<>();
    }

    // Constructors, getters, and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public List<MessageDetail> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageDetail> messages) {
        this.messages = messages;
    }

    public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public static class MessageDetail {
    	private String senderId ;
        private String message;
        private Date time;

        public MessageDetail() {
        }

        public MessageDetail(String message, Date time, String senderId) {
        	this.senderId = senderId;
            this.message = message;
            this.time = time;
        }

        // Getters and setters
        public String getSenderId() {
            return senderId;
        }

        public void setSenderId(String senderId) {
            this.senderId = senderId;
        }
        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

		
    }
}
