package com.example.platforme_iot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.platforme_iot.model.Documentation;
import com.example.platforme_iot.repository.DocumentationRepository;

@Service
public class DocumentationService {

    private final DocumentationRepository documentationRepository;

    @Autowired
    public DocumentationService(DocumentationRepository documentationRepository) {
        this.documentationRepository = documentationRepository;
    }

    public Documentation addDocumentation(Documentation documentation) {
        return documentationRepository.save(documentation);
    }
    
    
    public List<Documentation> getAllDocumentation() {
        // Add logic to fetch all documentation from MongoDB
        return documentationRepository.findAll();
    }
}
