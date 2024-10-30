package com.gamexo.backend.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllersException {


    @ExceptionHandler(ResponseExeption.class)
    public ResponseEntity<Map<String, String>> ResponseExGen(ResponseExeption ex) {
        Map<String, String> response = new HashMap<>();
        response.put(ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
