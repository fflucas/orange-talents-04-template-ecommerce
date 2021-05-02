package br.com.zupacademy.fabio.mercadolivre.product;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class HasRepeatedFeaturesValidator implements ConstraintValidator<HasRepeatedFeatures, Set<RequestFeature>> {
    @Override
    public void initialize(HasRepeatedFeatures constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Set<RequestFeature> requestFeatures, ConstraintValidatorContext context) {
        HashSet<String> featuresName = new HashSet<>();
        for (RequestFeature request : requestFeatures){
            if(!featuresName.add(request.getName())){
                return false;
            }
        }
        return true;
    }
}
