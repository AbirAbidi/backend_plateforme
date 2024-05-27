package com.example.platforme_iot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.platforme_iot.model.Documentation;
import com.example.platforme_iot.service.DocumentationService;

@RestController
@RequestMapping("/documentation")
public class DocumentationController {

    private final DocumentationService documentationService;

    @Autowired
    public DocumentationController(DocumentationService documentationService) {
        this.documentationService = documentationService;
    }

    @PostMapping
    public ResponseEntity<Documentation> addDocumentation(@RequestBody Documentation documentation) {
        Documentation savedDocumentation = documentationService.addDocumentation(documentation);
        return new ResponseEntity<>(savedDocumentation, HttpStatus.CREATED);
    }
    
    
    @GetMapping
    public ResponseEntity<List<Documentation>> getAllDocumentation() {
        List<Documentation> allDocumentation = documentationService.getAllDocumentation();
        return ResponseEntity.ok(allDocumentation);
    }
}
