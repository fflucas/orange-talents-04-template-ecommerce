package br.com.zupacademy.fabio.mercadolivre.product;

import br.com.zupacademy.fabio.mercadolivre.category.Category;
import br.com.zupacademy.fabio.mercadolivre.shared.EmailSender;
import br.com.zupacademy.fabio.mercadolivre.user.User;
import io.jsonwebtoken.lang.Assert;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int quantity;
    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST)
    private Set<Feature> features = new HashSet<>();
    @Column(nullable = false)
    private String description;
    @ManyToOne
    private Category category;
    @ManyToOne
    private User owner;
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private List<ProductPhoto> photos = new ArrayList<>();
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created_at;
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "product", cascade = CascadeType.MERGE)
    private List<Question> questions = new ArrayList<>();

    @Deprecated
    public Product() {
    }

    public Product(String name, BigDecimal price, int quantity, Set<RequestFeature> features,
                   String description, Category category, User owner) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.owner = owner;
        this.features.addAll(features.stream()
                .map(feature -> feature.convertToFeature(this))
                .collect(Collectors.toSet())
        );
        this.description = description;
        this.category = category;

        Assert.isTrue(this.features.size()>=3, "Product must have at least 3 features");
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public String getDescription() {
        return description;
    }

    public Category getCategory() {
        return category;
    }

    public User getOwner() {
        return owner;
    }

    public Calendar getCreated_at() {
        return created_at;
    }

    public List<ProductPhoto> getPhotos() {
        return photos;
    }

    public void newProductPhotos(List<ProductPhoto> photos) {
        this.photos.addAll(photos);
    }

    public void newReview(Review review) {
        this.reviews.add(review);
    }

    public void newQuestion(Question question, EmailSender emailSender) {
        int beforeAdd = questions.size();
        this.questions.add(question);
        int afterAdd = questions.size();
        Assert.isTrue(afterAdd>beforeAdd, "Something went wrong and the question was not recorded on the product");
        triggersEmailToSeller(question, emailSender);
    }

    private void triggersEmailToSeller(Question question, EmailSender emailSender) {
        String productOwner = question.getProduct().getOwner().getUsername();
        String productName = question.getProduct().getName();
        emailSender.simpleMessage(productOwner, productName);
    }
}
