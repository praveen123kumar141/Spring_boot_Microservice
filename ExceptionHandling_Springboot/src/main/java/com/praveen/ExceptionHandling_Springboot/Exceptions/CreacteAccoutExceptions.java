package com.praveen.ExceptionHandling_Springboot.Exceptions;

import com.praveen.ExceptionHandling_Springboot.Entity.CompositPrimaryKey_Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class CreacteAccoutExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handle(Exception e) {

        return new ResponseEntity<Object>(e.getMessage(),HttpStatus.BAD_GATEWAY);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(AccountNotFound.class)
    public ResponseEntity<?> invalidAccountDetails(AccountNotFound accountNotFound1){
        Map<String,String> map=new HashMap();
        map.put("Error Message",accountNotFound1.getErrorMessage());
        map.put("Error Code","400");
        map.put("Time Stamp",new Date().toString());
        return ResponseEntity.badRequest().body(map);
    }

}
