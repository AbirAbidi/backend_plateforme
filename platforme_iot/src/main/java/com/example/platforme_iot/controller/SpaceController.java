package com.example.platforme_iot.controller;

import com.example.platforme_iot.model.Space;
import com.example.platforme_iot.repository.SpaceRepository;
import com.example.platforme_iot.service.SpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaces") // Adjust the base path as needed
public class SpaceController {

    @Autowired
    private SpaceService spaceService;
    
    @Autowired
    private SpaceRepository spaceRepository;

    @PostMapping("/add")
    public ResponseEntity<Space> addSpace(@RequestBody Space space) {
        Space createdSpace = spaceService.addSpace(space);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpace);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Space>> getSpacesByUserId(@PathVariable String userId) {
        List<Space> spaces = spaceService.getSpacesByUserId(userId);
        return ResponseEntity.ok().body(spaces);
    }

 // Add the new endpoint to delete a space by its ID
    @DeleteMapping("/delete-space/{id}")
    public ResponseEntity<?> deleteSpace(@PathVariable String id) {
        // Delete the space from MongoDB using its ID
        spaceRepository.deleteSpaceById(id);

        // Return a JSON response with a success message
        return ResponseEntity.ok().body("{\"message\": \"Space deleted successfully\"}");
    }
}
