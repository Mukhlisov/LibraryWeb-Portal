package com.github.mukhlisov.exceptions;


import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.security.Principal;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler({NoSuchElementException.class, NoResourceFoundException.class})
    public String handleNoSomethingException(Principal principal, final Exception ex, final Model model, HttpServletResponse response) {
        boolean isLoggedIn = principal != null;
        ExceptionEntity entity = new ExceptionEntity(
                HttpStatus.NOT_FOUND.value(),
                "Ничего не найдено, возможно вы допустили опечатку в запросе",
                ex.getMessage()
        );
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("entity", entity);
        response.setStatus(entity.getCode());
        return "errors/error";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Principal principal, Exception ex, Model model, HttpServletResponse response){
        boolean isLoggedIn = principal != null;
        ExceptionEntity entity = new ExceptionEntity(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Неизвестная ошибка",
                ex.getMessage()
        );
        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("entity", entity);
        response.setStatus(entity.getCode());
        return "errors/error";
    }

}
