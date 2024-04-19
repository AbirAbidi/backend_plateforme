package com.example.platforme_iot.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.platforme_iot.model.TemperatureData;

@Repository
public interface TemperatureDataRepository extends MongoRepository<TemperatureData, String> {
   // List<TemperatureData> findByDay(String day);
}
