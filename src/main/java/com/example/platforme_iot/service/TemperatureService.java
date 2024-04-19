package com.example.platforme_iot.service;

import com.example.platforme_iot.model.TemperatureData;
import com.example.platforme_iot.repository.TemperatureDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemperatureService {
	
	private final TemperatureDataRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureDataRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    public void saveTemperature(TemperatureData temperature) {
        temperatureRepository.save(temperature);
    }
    
    // New method to add temperature data with day and temperature
    public void addTemperature(String day, int temperature) {
        TemperatureData temperatureData = new TemperatureData();
        temperatureData.setDay(day);
        temperatureData.setTemperature(temperature);
        saveTemperature(temperatureData);
    }

}
