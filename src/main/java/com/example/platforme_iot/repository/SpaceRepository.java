package com.example.platforme_iot.repository;

import com.example.platforme_iot.model.Space;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpaceRepository extends MongoRepository<Space, String> {
    List<Space> findByUserId(String userId);
}
