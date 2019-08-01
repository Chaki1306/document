package com.example.document.service.impl;

import com.example.document.converter.DocumentConverter;
import com.example.document.dto.DocumentChangeRequestDto;
import com.example.document.dto.DocumentCreateRequestDto;
import com.example.document.exeption.CompanyNotFoundException;
import com.example.document.exeption.DocumentNotFoundException;
import com.example.document.model.Company;
import com.example.document.model.Document;
import com.example.document.repository.CompanyRepository;
import com.example.document.repository.DocumentRepository;
import com.example.document.service.SignDocument;
import com.example.document.service.SystemService;
import com.example.document.validator.impl.ValidatorImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Edi on 29.07.2019.
 */
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
@RequiredArgsConstructor
public class SystemServiceImpl implements SystemService {
    private final DocumentRepository documentRepository;
    private final CompanyRepository companyRepository;
    private final ValidatorImpl validator;
    private final DocumentConverter documentConverter;
    private final SignDocument signDocument;

    public Document createDocument(DocumentCreateRequestDto documentCreateRequestDto) throws CompanyNotFoundException {
        LocalDateTime localDateTimeNow = LocalDateTime.now();

        Company sender = companyRepository.findById(documentCreateRequestDto.getSenderId())
                .orElseThrow(() -> new CompanyNotFoundException("Компания c ID: " + documentCreateRequestDto.getSenderId() + " не существует."));
        Company recipient = companyRepository.findById(documentCreateRequestDto.getRecipientId())
                .orElseThrow(() -> new CompanyNotFoundException("Компания с ID: " + documentCreateRequestDto.getRecipientId() + " не существует."));

        validator.validateAllLimit(localDateTimeNow, documentCreateRequestDto.getName(), sender, recipient);

        Document document = documentConverter.toDocumentCreateRequestDto(documentCreateRequestDto, sender, recipient, localDateTimeNow);

        signDocument.signDocument(document);

        return documentRepository.save(document);
    }

    public Document signOrChangeDocument(Long documentId, DocumentChangeRequestDto documentChangeRequestDto) throws DocumentNotFoundException {
        LocalDateTime localDateTimeNow = LocalDateTime.now();

        validator.validateBanToTime(localDateTimeNow);

        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new DocumentNotFoundException("Документ с ID: " + documentId.toString() + " не существует."));

        if (Objects.equals(document.getName(), documentChangeRequestDto.getName()) &&
                Objects.equals(document.getInfo(), documentChangeRequestDto.getInfo())) {
            signDocument.signDocument(document);
            return documentRepository.save(document);
        } else {
            validator.validateNameDocument(documentChangeRequestDto.getName());
            documentConverter.toDocumentChangeRequestDto(document, documentChangeRequestDto);
            signDocument.signDocument(document);
            return documentRepository.save(document);
        }
    }
}
