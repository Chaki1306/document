package com.example.document.exeption;

/**
 * Created by Edi on 31.07.2019.
 */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
