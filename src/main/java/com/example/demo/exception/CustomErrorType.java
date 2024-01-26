package com.example.demo.exception;

import com.example.demo.dto.UtenteDTO;

public class CustomErrorType extends UtenteDTO {

    private String errorMessage;

    public CustomErrorType(final String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
