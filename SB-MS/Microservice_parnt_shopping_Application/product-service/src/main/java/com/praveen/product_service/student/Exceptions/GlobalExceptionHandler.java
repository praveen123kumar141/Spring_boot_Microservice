package com.praveen.product_service.student.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyServiceException.class)
    public ResponseEntity<?> internalServerError(MyServiceException msg){
        Map<String,String> map=new HashMap<>();
        map.put("timestamp",new Date().toString());
        map.put("Error message",msg.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<?> resourceNotFound(ResourceNotFound msg){
        Map<String,String> map=new HashMap<>();
        map.put("timestamp",new Date().toString());
        map.put("Error message",msg.getMessage());
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }

}
