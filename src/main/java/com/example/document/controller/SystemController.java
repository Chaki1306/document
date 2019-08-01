package com.example.document.controller;

import com.example.document.converter.DocumentConverter;
import com.example.document.dto.DocumentChangeRequestDto;
import com.example.document.dto.DocumentCreateRequestDto;
import com.example.document.dto.DocumentDto;
import com.example.document.exeption.CompanyNotFoundException;
import com.example.document.exeption.DocumentNotFoundException;
import com.example.document.model.Document;
import com.example.document.service.SystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Edi on 30.07.2019.
 */
@RestController
@RequiredArgsConstructor
public class SystemController {

    private final SystemService systemService;
    private final DocumentConverter documentConverter;

    @PostMapping("/document")
    public ResponseEntity<DocumentDto> createDocument(@Valid @RequestBody DocumentCreateRequestDto documentCreateRequestDto) throws CompanyNotFoundException {
        Document document = systemService.createDocument(documentCreateRequestDto);
        DocumentDto dto = documentConverter.toDocument(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/document/{id}")
    public ResponseEntity<DocumentDto> signOrChangeDocument(@Valid @RequestBody DocumentChangeRequestDto documentChangeRequestDto,
                                               @PathVariable("id") Long documentId) throws DocumentNotFoundException {
        Document document = systemService.signOrChangeDocument(documentId, documentChangeRequestDto);
        DocumentDto dto = documentConverter.toDocument(document);
        return ResponseEntity.ok(dto);
    }
}
