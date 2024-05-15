package com.github.mukhlisov.customAnnotations;


import com.github.mukhlisov.validators.YearNotInFutureValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {YearNotInFutureValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface YearNotInFuture {
    String message() default "Год не может быть больше текущего";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
