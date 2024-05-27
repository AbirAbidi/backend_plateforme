package com.example.platforme_iot.service;

import com.example.platforme_iot.model.Space;
import com.example.platforme_iot.repository.SpaceRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpaceServiceImpl implements SpaceService {

    @Autowired
    private SpaceRepository spaceRepository;

    @Override
    public Space addSpace(Space space) {
        return spaceRepository.save(space);
    }

    @Override
    public List<Space> getSpacesByUserId(String userId) {
        return spaceRepository.findByUserId(userId);
    }
    
    
    
    @Override
    public void deleteSpaceById(String id) {
        spaceRepository.deleteById(id);
    }
    }
