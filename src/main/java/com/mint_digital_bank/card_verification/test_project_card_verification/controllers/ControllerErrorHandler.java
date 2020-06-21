package com.mint_digital_bank.card_verification.test_project_card_verification.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler({RuntimeException.class,
    Exception.class})
    public ResponseEntity<Map<String,Object>> customResourceNotFound(HttpServletRequest request, Exception ex){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("success",false);
        response.put("error","Error: "+ex.getMessage());
        return new ResponseEntity<>(response,BAD_REQUEST);
    }
}
