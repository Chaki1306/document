package com.example.document.exeption;

import javassist.NotFoundException;

/**
 * Created by Edi on 30.07.2019.
 */
public class CompanyNotFoundException extends NotFoundException {

    public CompanyNotFoundException(String msg) {
        super(msg);
    }
}
