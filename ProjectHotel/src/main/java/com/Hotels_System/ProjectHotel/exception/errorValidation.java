package com.Hotels_System.ProjectHotel.exception;

import org.springframework.validation.FieldError;

public record errorValidation(
        String field,
        String message
){
    public errorValidation(FieldError fieldError){
        this(fieldError.getField(), fieldError.getDefaultMessage());
    }
}
