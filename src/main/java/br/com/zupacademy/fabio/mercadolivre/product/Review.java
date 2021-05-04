package br.com.zupacademy.fabio.mercadolivre.product;

import br.com.zupacademy.fabio.mercadolivre.user.User;

import javax.persistence.*;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int note;
    private String title;
    @Column(length = 500)
    private String description;
    @ManyToOne
    private User owner;
    @ManyToOne
    private Product product;

    @Deprecated
    public Review() {
    }

    public Review(int note, String title, String description, User owner, Product product) {
        this.note = note;
        this.title = title;
        this.description = description;
        this.owner = owner;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public int getNote() {
        return note;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
