package com.example.document.exeption;

import javassist.NotFoundException;

/**
 * Created by Edi on 30.07.2019.
 */
public class DocumentNotFoundException extends NotFoundException {
    public DocumentNotFoundException(String msg) {
        super(msg);
    }
}
