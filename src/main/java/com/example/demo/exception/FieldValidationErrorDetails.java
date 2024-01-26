package com.example.demo.exception;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class FieldValidationErrorDetails {

    private String error_title;
    private int error_status;
    private String error_detail;
    private long error_timeStamp;
    private String error_path;
    private String error_developer_Message;
    private Map<String, List<FieldValidationError>> errors = new HashMap<String, List<FieldValidationError>>();
}
