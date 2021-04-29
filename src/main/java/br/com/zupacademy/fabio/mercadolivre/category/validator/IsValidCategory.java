package br.com.zupacademy.fabio.mercadolivre.category.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IsValidCategoryValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidCategory {
    String message() default "Value field must exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
