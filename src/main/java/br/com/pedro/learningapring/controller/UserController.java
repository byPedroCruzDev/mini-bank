package br.com.pedro.learningapring.controller;

import br.com.pedro.learningapring.dto.CreatDeposit;
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
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<User> retriveUser(@PathVariable final String id) throws Exception{
        final User user = userServices.retriveUser(Long.parseLong(id));

        return new ResponseEntity<User>(user,HttpStatus.OK );
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody final User userData, @PathVariable final String id) throws Exception{
        final User user = userServices.updateUser(userData, Long.parseLong(id));

        return new ResponseEntity<User>(user,HttpStatus.OK );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable final String id) throws Exception{
        final User user = userServices.deleteUser(Long.parseLong(id));

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT );
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> creatDeposit(@RequestBody final CreatDeposit depositData, @PathVariable final String id) throws Exception{
        final User userDeposit = userServices.createDeposit(depositData, Long.parseLong(id));

        return new ResponseEntity<User>(userDeposit, HttpStatus.OK );
    }

}
