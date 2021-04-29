package br.com.zupacademy.fabio.mercadolivre.category;

import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToOne
    private Category mother_category;

    @Deprecated
    public Category() {
    }

    public Category(String name, Category mother_category) {
        Assert.isTrue(!name.isBlank(), "Category name must exists");
        Assert.isTrue(mother_category!=null, "Mother category must exists");
        this.name = name;
        this.mother_category = mother_category;
    }

    public Category(String name) {
        Assert.isTrue(!name.isBlank(), "Category name must exists");
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getMother_category() {
        return mother_category;
    }
}
