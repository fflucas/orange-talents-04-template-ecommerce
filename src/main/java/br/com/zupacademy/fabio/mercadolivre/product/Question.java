package br.com.zupacademy.fabio.mercadolivre.product;

import br.com.zupacademy.fabio.mercadolivre.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created_at;
    @ManyToOne
    private User owner;
    @ManyToOne
    private Product product;

    @Deprecated
    public Question() {
    }

    public Question(String title, User owner, Product product) {
        this.title = title;
        this.owner = owner;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Calendar getCreated_at() {
        return created_at;
    }
}
