package br.com.zupacademy.fabio.mercadolivre.category.validator;

import br.com.zupacademy.fabio.mercadolivre.category.Category;
import br.com.zupacademy.fabio.mercadolivre.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class IsUniqueNameValidator implements ConstraintValidator<IsUniqueName, String> {

    private CategoryRepository categoryRepository;

    @Autowired
    public IsUniqueNameValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialize(IsUniqueName constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        Optional<Category> optionalCategory = categoryRepository.findByName(name);
        return optionalCategory.isEmpty();
    }
}
