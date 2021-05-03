package br.com.zupacademy.fabio.mercadolivre.product.controller;

import br.com.zupacademy.fabio.mercadolivre.product.Product;
import br.com.zupacademy.fabio.mercadolivre.product.RequestReview;
import br.com.zupacademy.fabio.mercadolivre.product.Review;
import br.com.zupacademy.fabio.mercadolivre.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/product")
public class CreateReview {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping("/{id}/review")
    @Transactional
    public ResponseEntity<Object> create(@PathVariable Long id,
                                         @AuthenticationPrincipal User loggedUser,
                                         @RequestBody @Valid RequestReview requestReview){
        Product product = entityManager.find(Product.class, id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        Review review = requestReview.convertToReview(product, loggedUser);
        product.newReview(review);
        entityManager.merge(product);
        return ResponseEntity.ok(review);
    }
}
