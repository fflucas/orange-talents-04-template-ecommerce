package br.com.zupacademy.fabio.mercadolivre.user.controller;

import br.com.zupacademy.fabio.mercadolivre.user.User;
import br.com.zupacademy.fabio.mercadolivre.user.UserRepository;
import br.com.zupacademy.fabio.mercadolivre.user.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/user")
public class CreateUser {

    private UserRepository userRepository;

    @Autowired
    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserRequest userRequest){
        User user = userRequest.convertRequestToModel();
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
