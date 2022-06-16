package com.sample.customerservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
@Documented
public @interface IAgeAnnotation {
    String message() default "Age is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
