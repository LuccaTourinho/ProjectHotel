package com.Hotels_System.ProjectHotel.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Error400 {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity treatInvalidData(MethodArgumentNotValidException ex){
        var errors = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(errors.stream().map(errorValidation::new).toList());
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<Object> treatDataIntegrityViolation(DataIntegrityViolationException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", "Erro de integridade de dados: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({ HotelRoomNumberException.class })
    public ResponseEntity<Object> treatHotelRoomNumberException(HotelRoomNumberException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler({ HotelUniqueNameException.class })
    public ResponseEntity<Object> treatHotelRoomNumberException(HotelUniqueNameException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
