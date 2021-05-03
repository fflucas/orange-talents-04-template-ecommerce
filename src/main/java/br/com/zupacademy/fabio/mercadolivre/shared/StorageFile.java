package br.com.zupacademy.fabio.mercadolivre.shared;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageFile {
    List<String> save(List<MultipartFile> files);
}
