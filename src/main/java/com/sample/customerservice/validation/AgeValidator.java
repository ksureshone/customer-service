package com.sample.customerservice.validation;

import org.apache.tomcat.jni.Local;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public class AgeValidator implements ConstraintValidator<IAgeAnnotation, LocalDate> {

    private Date validDate;

    @Override
    public void initialize(IAgeAnnotation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
        LocalDate now = LocalDate.now();
        Period between = Period.between(now, dob);
        int years = between.getYears();
        if(years >= 18){
            return true;
        }

        return false;
    }
}
