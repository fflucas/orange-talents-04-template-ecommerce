package br.com.zupacademy.fabio.mercadolivre.product;

import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;

@Entity
public class ProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    @ManyToOne
    private Product product;

    @Deprecated
    public ProductPhoto() {
    }

    public ProductPhoto(String url, Product product) {
        Assert.notNull(url, "URL of the photo can't be null");
        Assert.notNull(product, "Photo must be tied to one product");
        this.url = url;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

}
