package br.com.zupacademy.fabio.mercadolivre.shared;

import io.jsonwebtoken.lang.Assert;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Profile("dev")
public class StorageFileFake implements StorageFile{
    @Override
    public List<String> save(List<MultipartFile> files) {
        List<String> urls = new ArrayList<>();
        for(MultipartFile file : files){
                Assert.notNull(file, "File must exist");
            try{
                String hash = DigestUtils.md5DigestAsHex(file.getInputStream());
                String baseName = FilenameUtils.getBaseName(file.getOriginalFilename());
                String extension = FilenameUtils.getExtension(file.getOriginalFilename());
                String hashFileName = Objects.requireNonNull(baseName).concat(hash).concat("."+extension);
                String url = "http://localhost:8080/files/".concat(hashFileName);
                urls.add(url);
            }catch (IOException e){
                System.out.println(e);
            }
        }
        return urls;
    }
}
