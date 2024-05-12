package com.github.mukhlisov.exceptions;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException ex, Model model, HttpServletResponse response){
        model.addAttribute("messageHeader", "Ничего не найдено");
        model.addAttribute("messageBody", ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return "errors/no_content";
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public String handleNoResourceFoundException(NoResourceFoundException ex, Model model, HttpServletResponse response){
        model.addAttribute("messageHeader", "Ничего не найдено");
        model.addAttribute("messageBody", ex.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return "errors/no_content";
    }

}
