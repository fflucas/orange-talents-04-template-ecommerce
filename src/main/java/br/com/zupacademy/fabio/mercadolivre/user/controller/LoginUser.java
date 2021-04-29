package br.com.zupacademy.fabio.mercadolivre.user.controller;

import br.com.zupacademy.fabio.mercadolivre.shared.Response;
import br.com.zupacademy.fabio.mercadolivre.shared.config.security.TokenUtil;
import br.com.zupacademy.fabio.mercadolivre.user.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/login")
public class LoginUser {

    private AuthenticationManager authenticationManager;
    private final TokenUtil tokenUtil;
    @Autowired
    public LoginUser(AuthenticationManager authenticationManager, TokenUtil tokenUtil) {
        this.authenticationManager = authenticationManager;
        this.tokenUtil = tokenUtil;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> login(@RequestBody @Valid LoginRequest loginRequest){
        UsernamePasswordAuthenticationToken credentials = loginRequest.convertToTokenAuth();

        try{
            Authentication authenticate = authenticationManager.authenticate(credentials);
            String token = tokenUtil.generateToken(authenticate);
            return ResponseEntity.ok(new Response("Bearer " + token));
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(e.getLocalizedMessage()));
        }
    }

}
