package br.com.zupacademy.fabio.mercadolivre.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResponseProduct {
    private String name;
    private BigDecimal price;
    private List<ProductPhoto> photos = new ArrayList<>();
    private double average_rating;
    private int count_rating;
    private List<Review> reviews = new ArrayList<>();
    private Set<Feature> features = new HashSet<>();
    private List<Question> questions = new ArrayList<>();

    public ResponseProduct() {
    }

    public void build(Product product){
        name = product.getName();
        price = product.getPrice();
        photos = product.getPhotos();
        features = product.getFeatures();
        reviews = product.getReviews();
        questions = product.getQuestions();
        average_rating = reviews.stream()
                .mapToDouble(Review::getNote)
                .average()
                .orElse(Double.NaN);
        count_rating = (int) reviews.stream()
                .mapToInt(Review::getNote)
                .count();
    }

    public List<ProductPhoto> getPhotos() {
        return photos;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<Feature> getFeatures() {
        return features;
    }

    public double getAverage_rating() {
        return average_rating;
    }

    public int getCount_rating() {
        return count_rating;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
