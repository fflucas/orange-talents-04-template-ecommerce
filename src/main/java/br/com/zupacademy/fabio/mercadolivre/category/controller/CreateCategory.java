package br.com.zupacademy.fabio.mercadolivre.category.controller;

import br.com.zupacademy.fabio.mercadolivre.category.Category;
import br.com.zupacademy.fabio.mercadolivre.category.CategoryRepository;
import br.com.zupacademy.fabio.mercadolivre.category.RequestCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/category")
public class CreateCategory {

    private CategoryRepository repository;

    @Autowired
    public CreateCategory(CategoryRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody @Valid RequestCategory requestCategory){
        Category category = requestCategory.convertToCategory(repository);
        repository.save(category);
        return ResponseEntity.ok(category);
    }
}
