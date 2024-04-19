package com.example.platforme_iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.platforme_iot.model.TemperatureData;
import com.example.platforme_iot.repository.TemperatureDataRepository;
import com.example.platforme_iot.service.TemperatureService;

import java.util.List;

@RestController
@RequestMapping("/api/temperature")
public class TemperatureController {

    private final TemperatureDataRepository temperatureRepository;
    private final TemperatureService temperatureService;


     @Autowired
    public TemperatureController(TemperatureDataRepository temperatureRepository, TemperatureService temperatureService) {
        this.temperatureRepository = temperatureRepository;
        this.temperatureService = temperatureService;
    }

    @GetMapping("/all")
    public List<TemperatureData> getAllTemperatureData() {
        List<TemperatureData> data = temperatureRepository.findAll();
        System.out.println("Fetched Temperature Data: " + data);
        return data;
    }
    
  
    
 // New endpoint to add temperature data with day and temperature via POST request
    @PostMapping("/add")
    public void addTemperature(@RequestBody TemperatureData temperatureData) {
        temperatureService.saveTemperature(temperatureData);
    }
    
    // New endpoint to add temperature data with day and temperature via POST request
    @PostMapping("/addWithDayAndTemperature")
    public void addTemperatureWithDayAndTemperature(@RequestParam String day, @RequestParam int temperature) {
        temperatureService.addTemperature(day, temperature);
    }
}


