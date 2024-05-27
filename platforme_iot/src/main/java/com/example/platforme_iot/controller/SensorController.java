package com.example.platforme_iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.platforme_iot.model.Sensor;
import com.example.platforme_iot.model.Space;
import com.example.platforme_iot.repository.SensorRepository;
import com.example.platforme_iot.repository.SpaceRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
public class SensorController {
    private final SensorRepository sensorRepository;
    @Autowired
    private SpaceRepository spaceRepository;

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
        // Save the sensor data using the repository
        Sensor savedSensor = sensorRepository.save(sensorData);
        
        // Update the sensorList array in the corresponding space document
        Space space = spaceRepository.findById(sensorData.getSpaceId()).orElse(null);
        if (space != null) {
            List<String> sensorList = space.getSensorList();
            if (sensorList == null) {
                sensorList = new ArrayList<>();
            }
            sensorList.add(savedSensor.getId()); // Add the saved sensor ID to the list
            space.setSensorList(sensorList);
            spaceRepository.save(space);
        }
        
        // Return a JSON response with a success message
        return ResponseEntity.ok().body("{\"message\": \"Sensor data saved successfully\"}");
    }
    
    
    @PostMapping("/save-sensor-admin")
    public ResponseEntity<String> saveSensorAdmin(@RequestBody Sensor sensorData) {
        // Save the sensor data using the repository
        Sensor savedSensor = sensorRepository.save(sensorData);
        
        // Update the sensorList array in the corresponding space document
        Space space = spaceRepository.findById(sensorData.getSpaceId()).orElse(null);
        if (space != null) {
            List<String> sensorList = space.getSensorList();
            if (sensorList == null) {
                sensorList = new ArrayList<>();
            }
            sensorList.add(savedSensor.getId()); // Add the saved sensor ID to the list
            space.setSensorList(sensorList);
            spaceRepository.save(space);
        }
        
        // Return a JSON response with a success message
        return ResponseEntity.ok().body("{\"message\": \"Sensor data saved successfully\"}");
    }
  

    @GetMapping("/get-sensors-by-spaceId")
    public List<Sensor> getSensorsBySpaceId(@RequestParam String spaceId) {
        return sensorRepository.findBySpaceId(spaceId);
    }
    
    
 // Add the new endpoint to delete a sensor by its ID
    @DeleteMapping("/delete-sensor/{sensorId}")
    public ResponseEntity<String> deleteSensor(@PathVariable String sensorId) {
        // Delete the sensor from MongoDB using its ID
        sensorRepository.deleteById(sensorId);

        // Return a JSON response with a success message
        return ResponseEntity.ok().body("{\"message\": \"Sensor deleted successfully\"}");
    }
}
