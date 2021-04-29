package br.com.zupacademy.fabio.mercadolivre.shared.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    //authorization uri's
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/v1/user/**").permitAll()
                .antMatchers("/v1/category/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}