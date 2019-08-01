package com.example.document.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by Edi on 30.07.2019.
 */
@Getter
@Component
public class LimitationSettings {
    @Value("${banSignWith}") private String banSignWith;
    @Value("${banSignBy}") private String banSignBy;
    @Value("${limitDocumentForOneCompany}")private Long limitDocumentForOneCompany;
    @Value("${limitCreateDocumentInTime}") private Long limitCreateDocumentInTime;
    @Value("${limitCountCreateDocumentInTime}") private Long limitCountCreateDocumentInTime;
    @Value("${limitDocumentCompanyToCompany}") private Long limitDocumentCompanyToCompany;
}
