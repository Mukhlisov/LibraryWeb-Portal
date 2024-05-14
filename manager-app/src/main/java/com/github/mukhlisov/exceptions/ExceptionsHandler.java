package com.github.mukhlisov.exceptions;

import java.util.NoSuchElementException;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(MaxUploadSizeExceededException exc, Model model, HttpServletResponse response) {
        ExceptionEntity entity = new ExceptionEntity(
                HttpStatus.PAYLOAD_TOO_LARGE.value(),
                "Файл превыфшает допустимый вес",
                "Вы загрузили файл весом больше 5 МБ"
        );
        model.addAttribute("entity", entity);
        response.setStatus(entity.getCode());
        return "errors/error;";
    }
    
    @ExceptionHandler({NoSuchElementException.class, NoResourceFoundException.class})
    public String handleNoSuchElementException(Exception exc, Model model, HttpServletResponse response){
        ExceptionEntity entity = new ExceptionEntity(
              HttpStatus.NOT_FOUND.value(),
                "Ничего не найдено, возможно вы допустили опечатку в запросе",
                exc.getMessage()
        );
        response.setStatus(entity.getCode());
        model.addAttribute("entity", entity);
        return "errors/error;";
    }
}
