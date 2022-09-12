package com.example.demo.controller;

import com.example.demo.service.exception.NoBooksFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler({ NoBooksFoundException.class })
    public ResponseEntity<String> handleException() {
        return new ResponseEntity<String>("NoBooksFound", HttpStatus.NOT_FOUND);
    }
}
