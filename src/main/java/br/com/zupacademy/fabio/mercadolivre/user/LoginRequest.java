package br.com.zupacademy.fabio.mercadolivre.user;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginRequest {
    @NotBlank @Email
    private String username;
    @NotBlank @Size(min = 6)
    private String password;

    @Deprecated
    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UsernamePasswordAuthenticationToken convertToTokenAuth(){
        Assert.notNull(username, "Username can not be null");
        Assert.notNull(password, "Password can not be null");
        return new UsernamePasswordAuthenticationToken(username, password);
    }
}
