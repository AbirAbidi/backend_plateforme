package com.example.platforme_iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.platforme_iot.model.Sensor;
import com.example.platforme_iot.repository.SensorRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class SensorController {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorController(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @GetMapping("/generate-unique-id")
    public ResponseEntity<?> generateUniqueId() {
        // Generate a unique ID (you can use UUID or any other method)
        String uniqueId = UUID.randomUUID().toString();
        return ResponseEntity.ok(Collections.singletonMap("uniqueId", uniqueId));
    }
    
    @PostMapping("/save-sensor")
    public ResponseEntity<String> saveSensor(@RequestBody Sensor sensorData) {
        // Here you can process the received sensor data
        System.out.println("Received sensor data: " + sensorData);
        
     // Save the sensor data using the repository
        sensorRepository.save(sensorData);
        // Return a JSON response with a success message
        return ResponseEntity.ok().body("{\"message\": \"Sensor data saved successfully\"}");
    }
    
  

    @GetMapping("/get-sensors-by-spaceId")
    public List<Sensor> getSensorsBySpaceId(@RequestParam String spaceId) {
        return sensorRepository.findBySpaceId(spaceId);
    }
}
