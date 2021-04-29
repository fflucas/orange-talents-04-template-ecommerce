package br.com.zupacademy.fabio.mercadolivre.user.validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class RawPassword {
    private String raw_password;

    public RawPassword(String raw_password) {
        Assert.notNull(raw_password, "Password must exists");
        Assert.isTrue(raw_password.length()>=6, "Password must be at least 6 characters");

        this.raw_password = raw_password;
    }

    public String encode(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(this.raw_password);
    }

    public String toString() {
        return raw_password;
    }
}
