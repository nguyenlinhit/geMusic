package vn.edu.tdmu.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Created by NguyenLinh on 02/02/2018
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {UniqueUsernameValidator.class})
public @interface UniqueUsername{
    String message() default "Such username already exists.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}