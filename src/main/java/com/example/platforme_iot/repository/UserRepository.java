package com.example.platforme_iot.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.platforme_iot.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmail(String email);
}
