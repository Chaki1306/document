package com.example.document.converter;

import com.example.document.dto.DocumentChangeRequestDto;
import com.example.document.dto.DocumentCreateRequestDto;
import com.example.document.dto.DocumentDto;
import com.example.document.model.Company;
import com.example.document.model.Document;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Created by Edi on 31.07.2019.
 */
@Component
public interface DocumentConverter {
    Document toDocumentCreateRequestDto(DocumentCreateRequestDto dto,
                                        Company sender,
                                        Company recipient,
                                        LocalDateTime time);
    void toDocumentChangeRequestDto(Document document, DocumentChangeRequestDto dto);
    DocumentDto toDocument(Document document);
}
