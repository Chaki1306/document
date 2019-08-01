package com.example.document.converter.impl;

import com.example.document.converter.DocumentConverter;
import com.example.document.dto.DocumentChangeRequestDto;
import com.example.document.dto.DocumentCreateRequestDto;
import com.example.document.dto.DocumentDto;
import com.example.document.model.Company;
import com.example.document.model.Document;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Created by Edi on 31.07.2019.
 */
@Component
@Transactional(readOnly = true)
public class DocumentConverterImpl implements DocumentConverter {

    @Override
    public Document toDocumentCreateRequestDto(DocumentCreateRequestDto dto,
                                               Company sender,
                                               Company recipient,
                                               LocalDateTime time) {
        Document document = new Document();
        document.setName(dto.getName());
        document.setInitiator(sender);
        document.setInitiationDateTime(time);
        document.setSender(sender);
        document.setRecipient(recipient);
        document.setInfo(dto.getInfo());
        return document;
    }

    @Override
    public void toDocumentChangeRequestDto(Document document, DocumentChangeRequestDto dto) {
        Company sender = document.getRecipient();
        Company recipient = document.getSender();

        document.setName(dto.getName());
        document.setInfo(dto.getInfo());
        document.setSender(sender);
        document.setRecipient(recipient);
        document.setElectronicSignatureSender(false);
    }

    @Override
    public DocumentDto toDocument(Document document) {
        return DocumentDto.builder()
                .nameDocument(document.getName())
                .nameInitiator(document.getInitiator().getName())
                .initiationDateTime(document.getInitiationDateTime().toString())
                .nameSender(document.getSender().getName())
                .electronicSignatureSender(document.isElectronicSignatureSender())
                .nameRecipient(document.getRecipient().getName())
                .electronicSignatureRecipient(document.isElectronicSignatureRecipient())
                .info(document.getInfo())
                .build();
    }
}
