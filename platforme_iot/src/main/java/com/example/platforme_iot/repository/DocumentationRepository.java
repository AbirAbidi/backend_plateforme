package com.example.platforme_iot.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.platforme_iot.model.Documentation;

public interface DocumentationRepository extends MongoRepository<Documentation, String> {
}