package com.example.platforme_iot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.platforme_iot.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {
	
	 // Custom query method to find messages by the sender's user ID
    List<Message> findByUserId(String userId);

	List<Message> findByIsRead(boolean b);

	List<Message> findByAdmin(String b);

	List<Message> findByAdminOrAdminIsNull(String userId);

	List<Message> findByAdminNotNull();

	List<Message> findByUserIdAndIsRead(String userId, boolean b);
    


	
}

