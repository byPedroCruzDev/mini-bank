package br.com.pedro.learningapring.controller;

import br.com.pedro.learningapring.model.User;
import br.com.pedro.learningapring.service.UserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final User userData){
        final User createdUser = userServices.createUser(userData);

        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<User>> readUser(){
        final List<User> allUsers  = userServices.readUser();

        return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
    }

}
