package br.com.zupacademy.fabio.mercadolivre.shared.validator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IsValidValidator implements ConstraintValidator<IsValid, Long> {

    @PersistenceContext
    private EntityManager entityManager;
    private String fieldName;
    private Class<?> domainClass;

    @Override
    public void initialize(IsValid constraintAnnotation) {
        domainClass = constraintAnnotation.domainClass();
        fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        if(value == null){
            return true;
        }

        Query query = entityManager.createQuery("select 1 from "+domainClass.getName()+" where "+fieldName+"=:value");
        query.setParameter("value", value);
        List<?> list = query.getResultList();
        return !list.isEmpty();
    }
}
