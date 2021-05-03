package br.com.zupacademy.fabio.mercadolivre.product.controller;

import br.com.zupacademy.fabio.mercadolivre.product.Product;
import br.com.zupacademy.fabio.mercadolivre.product.ProductPhoto;
import br.com.zupacademy.fabio.mercadolivre.product.RequestPhoto;
import br.com.zupacademy.fabio.mercadolivre.shared.StorageFile;
import br.com.zupacademy.fabio.mercadolivre.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class CreatePhoto {

    @PersistenceContext
    private EntityManager manager;
    private StorageFile storageFile;

    @Autowired
    public CreatePhoto(EntityManager manager, StorageFile storageFile) {
        this.manager = manager;
        this.storageFile = storageFile;
    }

    @PostMapping("/{id}/photo")
    @Transactional
    public ResponseEntity<Object> create(@AuthenticationPrincipal User loggedUser,
                                         @PathVariable Long id,
                                         @Valid RequestPhoto requestPhoto ){

        Product product = manager.find(Product.class, id);
        if(product == null){
            return ResponseEntity.notFound().build();
        }
        Long ownerId = product.getOwner().getId();
        if(!ownerId.equals(loggedUser.getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<ProductPhoto> productPhotos = requestPhoto.convertToProductPhoto(storageFile, product);
        product.setPhotos(productPhotos);
        manager.merge(product);
        return ResponseEntity.ok(productPhotos);
    }
}
