package br.com.zupacademy.fabio.mercadolivre.user;

import br.com.zupacademy.fabio.mercadolivre.user.validator.RawPassword;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;

@Entity
public class User implements UserDetails {

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

    public User(String username, RawPassword raw_password) {
        Assert.notNull(username, "Username must exists");
        Assert.notNull(raw_password, "Password must exists");

        this.username = username;
        this.password = raw_password.encode();

        Assert.isTrue(matches(raw_password), "Password must be encoded");
    }

    public boolean matches(RawPassword rawPassword){
        return new BCryptPasswordEncoder().matches(rawPassword.toString(), this.password);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public Calendar getCreated_at() {
        return created_at;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
