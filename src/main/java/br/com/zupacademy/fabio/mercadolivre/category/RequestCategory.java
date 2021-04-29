package br.com.zupacademy.fabio.mercadolivre.category;

import br.com.zupacademy.fabio.mercadolivre.shared.validator.IsUnique;
import br.com.zupacademy.fabio.mercadolivre.shared.validator.IsValid;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class RequestCategory {
    @NotBlank @IsUnique(domainClass = Category.class, fieldName = "name")
    private String name;
    @IsValid(domainClass = Category.class, fieldName = "id")
    private Long mother_category_id;

    @Deprecated
    public RequestCategory() {
    }

    public RequestCategory(String name, Long mother_category_id) {
        this.name = name;
        this.mother_category_id = mother_category_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMother_category_id(Long mother_category_id) {
        this.mother_category_id = mother_category_id;
    }

    public Category convertToCategory(CategoryRepository repository){
        if(mother_category_id != null){
            Optional<Category> optionalCategory = repository.findById(mother_category_id);
            Assert.isTrue(optionalCategory.isPresent(), "Mother category must be valid");
            return new Category(name, optionalCategory.get());
        }
        return new Category(name);
    }
}
