package com.example.demo.exception;

import com.example.demo.enums.MessageType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldValidationError {

    private String filed;
    private String message;
    private MessageType messageType;
}
