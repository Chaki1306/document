package com.example.document.validator.impl;

import com.example.document.config.LimitationSettings;
import com.example.document.exeption.ValidationException;
import com.example.document.model.Company;
import com.example.document.repository.DocumentRepository;
import com.example.document.validator.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Created by Edi on 31.07.2019.
 */
@Component
@RequiredArgsConstructor
public class ValidatorImpl implements Validator {

    private final LimitationSettings limitationSettings;
    private final DocumentRepository documentRepository;

    public void validateAllLimit(LocalDateTime localDateTimeNow,
                                 String nameDocument,
                                 Company sender,
                                 Company resipient) {

        validateBanToTime(localDateTimeNow);

        validateNameDocument(nameDocument);

        if (isLimitDocumentForOneCompany(sender)) {
            throw new ValidationException("Превышен лимит  документооборота");
        }

        if (isLimitDocumentCompanyToCompany(sender, resipient)) {
            throw new ValidationException("Превышен лимит документооборота между двумя компаниями");
        }

        if (isMaxCountCreateDocument(localDateTimeNow, sender)) {
            throw new ValidationException("Превышен лимит создания документов");
        }
    }

    public void validateBanToTime(LocalDateTime localDateTimeNow) {
        if (isBanToTime(localDateTimeNow)) {
            throw new ValidationException("В настоящий момент подписание, изменение, создание документа запрещено.");
        }
    }

    public void validateNameDocument(String name) {
        if (isNameDocument(name)) {
            throw new ValidationException("Документ с таким название уже существует");
        }
    }

    private boolean isBanToTime(LocalDateTime localDateTimeNow) {
        LocalTime banSignWithTime = LocalTime.parse(limitationSettings.getBanSignWith());
        LocalTime banSignByTime = LocalTime.parse(limitationSettings.getBanSignBy());
        LocalDateTime banSignWithDateTime;
        LocalDateTime banSignByDateTime;
        if (banSignByTime.isBefore(banSignWithTime)) {
            banSignWithDateTime = LocalDateTime.of(localDateTimeNow.toLocalDate(), banSignWithTime);
            banSignByDateTime = LocalDateTime.of(localDateTimeNow.toLocalDate().plusDays(1L), banSignByTime);
        } else {
            banSignWithDateTime = LocalDateTime.of(LocalDate.now(), banSignWithTime);
            banSignByDateTime = LocalDateTime.of(LocalDate.now(), banSignByTime);
        }

        return localDateTimeNow.isAfter(banSignWithDateTime) && localDateTimeNow.isBefore(banSignByDateTime);
    }

    private boolean isMaxCountCreateDocument(LocalDateTime localDateTimeNow, Company sender) {
        Timestamp timestampWith = Timestamp.valueOf(localDateTimeNow.minusHours(limitationSettings.getLimitCreateDocumentInTime()));
        Timestamp timestampBy = Timestamp.valueOf(localDateTimeNow);
        Long limitCountCreateDocumentInTime = limitationSettings.getLimitCountCreateDocumentInTime();
        return documentRepository.isMaxCountCreateDocument(timestampWith, timestampBy, limitCountCreateDocumentInTime, sender.getId());
    }

    private boolean isNameDocument(String name) {
        return documentRepository.isNameDocument(name);
    }

    private boolean isLimitDocumentForOneCompany(Company sender) {
        Long countDocumentForCompanyInRuntime = documentRepository.countDocumentForCompanyInRuntime(sender.getId());
        Long limitDocumentForOneCompany = limitationSettings.getLimitDocumentForOneCompany();
        return Objects.equals(countDocumentForCompanyInRuntime, limitDocumentForOneCompany);
    }

    private boolean isLimitDocumentCompanyToCompany(Company sender, Company recipient) {
        Long countDocumentCompanyToCompany = documentRepository.countDocumentCompanyToCompany(sender.getId(), recipient.getId());
        Long limitDocumentCompanyToCompany = limitationSettings.getLimitDocumentCompanyToCompany();
        return Objects.equals(countDocumentCompanyToCompany, limitDocumentCompanyToCompany);
    }
}
