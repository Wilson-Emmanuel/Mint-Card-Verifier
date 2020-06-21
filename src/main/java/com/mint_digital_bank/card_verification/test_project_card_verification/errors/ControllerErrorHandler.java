package com.mint_digital_bank.card_verification.test_project_card_verification.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ControllerErrorHandler {

    @ExceptionHandler(CardNotFoundException.class)
    public ResponseEntity<Map<String,Object>> cardNotFoundExceptionHandler(HttpServletRequest request, CardNotFoundException ex){
        return this.createResponse("Card not found",NOT_FOUND);
    }

    @ExceptionHandler({RuntimeException.class,
    Exception.class})
    public ResponseEntity<Map<String,Object>> otherExceptionHandler(HttpServletRequest request, Exception ex){
        return this.createResponse(ex.getMessage(),BAD_REQUEST);
    }

    private ResponseEntity<Map<String,Object>> createResponse(String message, HttpStatus status){
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("success",false);
        response.put("error","Error: "+message);
        return new ResponseEntity<>(response,status);
    }
}
