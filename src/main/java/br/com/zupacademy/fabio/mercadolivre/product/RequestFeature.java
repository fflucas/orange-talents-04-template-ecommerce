package br.com.zupacademy.fabio.mercadolivre.product;

import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;

public class RequestFeature {
    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @Deprecated
    public RequestFeature() {
    }

    public RequestFeature(String name, String description) {
        Assert.notNull(name, "Feature name can not be null");
        Assert.notNull(description, "Feature description can not be null");
        this.name = name;
        this.description = description;
    }

    public Feature convertToFeature(Product product){
        return new Feature(name, description, product);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }
}
