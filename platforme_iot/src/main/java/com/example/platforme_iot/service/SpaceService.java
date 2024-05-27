package com.example.platforme_iot.service;

import java.util.List;

import com.example.platforme_iot.model.Space;

public interface SpaceService {

    Space addSpace(Space space);

    List<Space> getSpacesByUserId(String userId);

	void deleteSpaceById(String id);
    
}
