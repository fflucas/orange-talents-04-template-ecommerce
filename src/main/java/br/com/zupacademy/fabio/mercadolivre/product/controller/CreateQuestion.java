package br.com.zupacademy.fabio.mercadolivre.product.controller;


import br.com.zupacademy.fabio.mercadolivre.product.*;
import br.com.zupacademy.fabio.mercadolivre.shared.EmailSender;
import br.com.zupacademy.fabio.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/product")
public class CreateQuestion {

    @PersistenceContext
    private EntityManager entityManager;
    private EmailSender emailSender;

    @Autowired
    public CreateQuestion(EntityManager entityManager, EmailSender emailSender) {
        this.entityManager = entityManager;
        this.emailSender = emailSender;
    }

    @PostMapping("/{id}/question")
    @Transactional
    public ResponseEntity<Object> create(@PathVariable Long id,
                                         @AuthenticationPrincipal User loggedUser,
                                         @RequestBody @Valid RequestQuestion requestQuestion){
        Product product = entityManager.find(Product.class, id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        Question question = requestQuestion.convertToQuestion(product, loggedUser);
        product.newQuestion(question, emailSender);
        entityManager.merge(product);
        return ResponseEntity.ok(question);
    }
}
