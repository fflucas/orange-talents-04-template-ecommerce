package br.com.zupacademy.fabio.mercadolivre.user;

import br.com.zupacademy.fabio.mercadolivre.user.validation.RawPassword;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Calendar;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar created_at;

    @Deprecated
    public User() {
    }

    public User(String username, RawPassword password) {
        String raw_password = password.toString();

        Assert.notNull(username, "Username must exists");
        Assert.notNull(raw_password, "Password must exists");
        Assert.isTrue(raw_password.length()>=6, "Password must be at least 6 characters");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        this.username = username;
        this.password = encoder.encode(raw_password);

        Assert.isTrue(encoder.matches(raw_password, this.password), "Password must be encoded");
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Calendar getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Calendar created_at) {
        this.created_at = created_at;
    }
}
