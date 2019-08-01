package com.example.document.validator;

import com.example.document.model.Company;

import java.time.LocalDateTime;

/**
 * Created by Edi on 01.08.2019.
 */
public interface Validator {
    void validateAllLimit(LocalDateTime localDateTimeNow,
                          String nameDocument,
                          Company sender,
                          Company resipient);
    void validateBanToTime(LocalDateTime localDateTimeNow);
    void validateNameDocument(String name);
}
