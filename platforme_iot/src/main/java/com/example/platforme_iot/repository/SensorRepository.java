package com.example.platforme_iot.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.platforme_iot.model.Sensor;

public interface SensorRepository extends MongoRepository<Sensor, String> {
    List<Sensor> findBySpaceId(String spaceId);
}

