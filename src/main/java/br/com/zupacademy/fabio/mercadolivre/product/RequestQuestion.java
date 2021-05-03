package br.com.zupacademy.fabio.mercadolivre.product;

import br.com.zupacademy.fabio.mercadolivre.shared.EmailSender;
import br.com.zupacademy.fabio.mercadolivre.user.User;

import javax.validation.constraints.NotBlank;

public class RequestQuestion {
    @NotBlank
    private String title;

    @Deprecated
    public RequestQuestion() {
    }

    public RequestQuestion(String title) {
        this.title = title;
    }

    public Question convertToQuestion(Product product, User owner) {
        return new Question(title, owner, product);
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
