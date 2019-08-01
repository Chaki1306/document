package com.example.document.controller;

import com.example.document.dto.ExceptionResponseDto;
import com.example.document.exeption.CompanyNotFoundException;
import com.example.document.exeption.DocumentNotFoundException;
import com.example.document.exeption.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by Edi on 01.08.2019.
 */
@ControllerAdvice
public class SystemExceptionController {

    @ExceptionHandler(value = {CompanyNotFoundException.class,
            DocumentNotFoundException.class,
            ValidationException.class})
    public ResponseEntity<Object> businessException(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponseDto(ex.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> exception(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ExceptionResponseDto(ex.getMessage()));
    }
}
