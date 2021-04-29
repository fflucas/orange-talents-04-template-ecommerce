package br.com.zupacademy.fabio.mercadolivre.category;

import br.com.zupacademy.fabio.mercadolivre.category.validator.IsUniqueName;
import br.com.zupacademy.fabio.mercadolivre.category.validator.IsValidCategory;

import javax.validation.constraints.NotBlank;

public class RequestCategory {
    @NotBlank @IsUniqueName
    private String name;
    @IsValidCategory
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
            Category mother_category = repository.findById(mother_category_id).get();
            return new Category(name, mother_category);
        }
        return new Category(name);
    }
}
