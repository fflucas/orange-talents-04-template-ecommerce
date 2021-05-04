package br.com.zupacademy.fabio.mercadolivre.product.controller;

import br.com.zupacademy.fabio.mercadolivre.product.Product;
import br.com.zupacademy.fabio.mercadolivre.product.ResponseProduct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("/v1/product")
public class ShowProduct {

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity<Object> show(@PathVariable Long id){
        Product product = entityManager.find(Product.class, id);
        if (product == null){
            return ResponseEntity.notFound().build();
        }

        ResponseProduct responseProduct = new ResponseProduct();
        responseProduct.build(product);
        return ResponseEntity.ok(responseProduct);
    }
}
