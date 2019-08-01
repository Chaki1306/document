package com.example.document.service.impl;

import com.example.document.model.Document;
import com.example.document.service.SignDocument;
import org.springframework.stereotype.Component;

/**
 * Created by Edi on 31.07.2019.
 */
@Component
public class SignDocumentImpl implements SignDocument {
    @Override
    public void signDocument(Document document) {
        if (document.isElectronicSignatureSender()) {
            document.setElectronicSignatureRecipient(true);
        } else {
            document.setElectronicSignatureSender(true);
        }
    }
}
