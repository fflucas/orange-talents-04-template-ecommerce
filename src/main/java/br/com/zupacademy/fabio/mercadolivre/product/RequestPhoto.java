package br.com.zupacademy.fabio.mercadolivre.product;

import br.com.zupacademy.fabio.mercadolivre.shared.StorageFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RequestPhoto {
    @Size(min = 1)
    private List<MultipartFile> file = new ArrayList<>();

    @Deprecated
    public RequestPhoto() {
    }

    public RequestPhoto(List<MultipartFile> file) {
        this.file = file;
    }

    public List<ProductPhoto> convertToProductPhoto(StorageFile storage, Product product){
        List<String> urls = storage.save(file);
        List<ProductPhoto> productPhotos = urls.stream().map(
                url -> new ProductPhoto(url, product)
        ).collect(Collectors.toList());
        return productPhotos;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }
}
