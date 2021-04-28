package br.com.zupacademy.fabio.mercadolivre.user.validation;

public class RawPassword {
    private String raw_password;

    public RawPassword(String raw_password) {
        this.raw_password = raw_password;
    }

    @Override
    public String toString() {
        return this.raw_password;
    }
}
