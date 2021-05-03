package br.com.zupacademy.fabio.mercadolivre.product;

import br.com.zupacademy.fabio.mercadolivre.user.User;

import javax.validation.constraints.*;

public class RequestReview {
    @Min(1) @Max(5)
    private int note;
    @NotBlank
    private String title;
    @NotBlank @Size(max = 500)
    private String description;

    public RequestReview(int note, String title, String description) {
        this.note = note;
        this.title = title;
        this.description = description;
    }

    public Review convertToReview(Product product, User owner){
        return new Review(note, title, description, owner, product);
    }
}
