package br.com.zupacademy.fabio.mercadolivre.user.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsUniqueEmailValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsUniqueEmail {
    String message() default "Value field must be unique";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

