package com.github.mukhlisov;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.PAYLOAD_TOO_LARGE).body("Файл превышает максимально допустимый размер!");
    }
    
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementExeption(NoSuchElementException exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<h1>Ресурс не найден</h1>" + "<h3>" + exc.getLocalizedMessage() + "</h3>");
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<?> handleNoResourceFoundException(NoResourceFoundException exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("<h1>Ресурс не найден</h1>" + "<h3>" + exc.getLocalizedMessage() + "</h3>");
    }
}
