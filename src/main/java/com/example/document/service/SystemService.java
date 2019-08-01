package com.example.document.service;

import com.example.document.dto.DocumentChangeRequestDto;
import com.example.document.dto.DocumentCreateRequestDto;
import com.example.document.exeption.CompanyNotFoundException;
import com.example.document.exeption.DocumentNotFoundException;
import com.example.document.model.Document;

/**
 * Created by Edi on 31.07.2019.
 */
public interface SystemService {
    Document createDocument(DocumentCreateRequestDto dto) throws CompanyNotFoundException;
    Document signOrChangeDocument(Long documentId, DocumentChangeRequestDto dto) throws DocumentNotFoundException;
}
