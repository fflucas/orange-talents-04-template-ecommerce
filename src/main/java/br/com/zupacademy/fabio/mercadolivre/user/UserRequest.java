package br.com.zupacademy.fabio.mercadolivre.user;

import br.com.zupacademy.fabio.mercadolivre.user.validation.RawPassword;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {
    @NotBlank @Email
    private String username;
    @NotBlank @Size(min = 6)
    private String password;

    @Deprecated
    public UserRequest() {
    }

    public UserRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User convertRequestToModel(){
        return new User(this.username, new RawPassword(this.password));
    }
}
