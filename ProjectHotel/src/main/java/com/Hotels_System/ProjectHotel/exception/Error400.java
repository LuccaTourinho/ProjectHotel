package com.Hotels_System.ProjectHotel.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.MetaDataAccessException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Error400 {

    @ExceptionHandler(MetaDataAccessException.class)
    public ResponseEntity treatInvalidData(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(errorValidation::new).toList());
    }
}
