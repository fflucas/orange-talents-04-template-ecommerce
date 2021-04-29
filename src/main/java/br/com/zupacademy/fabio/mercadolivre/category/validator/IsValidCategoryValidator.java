package br.com.zupacademy.fabio.mercadolivre.category.validator;

import br.com.zupacademy.fabio.mercadolivre.category.Category;
import br.com.zupacademy.fabio.mercadolivre.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class IsValidCategoryValidator implements ConstraintValidator<IsValidCategory, Long> {

    private CategoryRepository categoryRepository;
    @Autowired
    public IsValidCategoryValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initialize(IsValidCategory constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long category_id, ConstraintValidatorContext context) {
        if(category_id == null){
            return true;
        }
        Optional<Category> optionalCategory = categoryRepository.findById(category_id);
        return optionalCategory.isPresent();
    }
}
