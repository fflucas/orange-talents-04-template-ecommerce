package br.com.zupacademy.fabio.mercadolivre.product;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Feature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private Product product;

    @Deprecated
    public Feature() {
    }

    public Feature(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return name.equals(feature.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
