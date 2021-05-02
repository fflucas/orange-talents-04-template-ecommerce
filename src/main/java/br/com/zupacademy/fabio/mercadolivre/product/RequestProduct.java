package br.com.zupacademy.fabio.mercadolivre.product;

import br.com.zupacademy.fabio.mercadolivre.category.Category;
import br.com.zupacademy.fabio.mercadolivre.shared.validator.IsValid;
import br.com.zupacademy.fabio.mercadolivre.user.User;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class RequestProduct {
    @NotBlank
    private String name;
    @NotNull @Positive
    private BigDecimal price;
    @NotNull @Min(0)
    private int quantity;
    @NotNull @Size(min = 3) @HasRepeatedFeatures @Valid
    private Set<RequestFeature> features = new HashSet<>();
    @NotBlank @Size(max = 1000)
    private String description;
    @NotNull @IsValid(domainClass = Category.class, fieldName = "id")
    private Long category_id;

    @Deprecated
    public RequestProduct() {
    }

    public RequestProduct(String name, BigDecimal price, int quantity, Set<RequestFeature> features,
                          String description, Long category_id) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.features.addAll(features);
        this.description = description;
        this.category_id = category_id;
    }

    public Product convertToProduct(EntityManager entityManager, User owner){
        Category category = entityManager.find(Category.class, category_id);
        return new Product(name, price, quantity, features, description, category, owner);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setFeatures(Set<RequestFeature> features) {
        this.features = features;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
}
