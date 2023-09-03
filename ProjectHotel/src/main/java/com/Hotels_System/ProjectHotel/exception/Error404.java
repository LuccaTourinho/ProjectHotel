package com.Hotels_System.ProjectHotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class Error404{

    @ExceptionHandler({HotelNotFoundException.class})
    public ResponseEntity<Object> treatNotFound(HotelNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({RoomNotFoundException.class})
    public ResponseEntity<Object> treatNotFound(RoomNotFoundException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
