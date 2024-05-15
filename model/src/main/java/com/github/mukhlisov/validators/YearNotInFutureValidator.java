package com.github.mukhlisov.validators;

import com.github.mukhlisov.customAnnotations.YearNotInFuture;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class YearNotInFutureValidator implements ConstraintValidator <YearNotInFuture, Integer>{
    @Override
    public void initialize(YearNotInFuture constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer year, ConstraintValidatorContext constraintValidatorContext) {
        if (year == null){
            return true;
        }
        return year <= Year.now().getValue();
    }
}
