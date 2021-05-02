package br.com.zupacademy.fabio.mercadolivre.product;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = HasRepeatedFeaturesValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HasRepeatedFeatures {
    String message() default "Features name can not be duplicate";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
