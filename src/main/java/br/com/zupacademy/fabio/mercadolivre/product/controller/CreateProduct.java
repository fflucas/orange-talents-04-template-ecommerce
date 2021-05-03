package br.com.zupacademy.fabio.mercadolivre.product.controller;

import br.com.zupacademy.fabio.mercadolivre.product.Product;
import br.com.zupacademy.fabio.mercadolivre.product.RequestProduct;
import br.com.zupacademy.fabio.mercadolivre.user.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/product")
public class CreateProduct {

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @Transactional
    public ResponseEntity<Product> create(@RequestBody @Valid RequestProduct requestProduct){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Product product = requestProduct.convertToProduct(entityManager, user);
        entityManager.persist(product);
        return ResponseEntity.ok(product);
    }
}
