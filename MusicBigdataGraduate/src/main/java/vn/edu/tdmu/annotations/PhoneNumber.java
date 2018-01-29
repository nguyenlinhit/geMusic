package vn.edu.tdmu.annotations;

import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.FIELD;

import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Created by NguyenLinh on 01/29/2018
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {PhoneNumberValidator.class})
public @interface PhoneNumber{
    String message() default "Please enter a valid number.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}