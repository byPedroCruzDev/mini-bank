package br.com.pedro.learningapring.controller;

import br.com.pedro.learningapring.dto.CreatDeposit;
import br.com.pedro.learningapring.dto.UserDto;
import br.com.pedro.learningapring.model.User;
import br.com.pedro.learningapring.service.UserServices;
import jakarta.validation.Valid;
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

    private final UserServices userService;

    public UserController(UserServices userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto userdata) {
        System.out.println(userdata);
        System.out.println("------------");
        final User createdUser = userService.createUser(userdata);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);

    }

    @GetMapping
    public ResponseEntity<List<User>> readUsers() {

        final List<User> allUsers = userService.readUsers();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable final String id) {

        final User user = userService.retrieveUser(Long.parseLong(id));

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody final UserDto userData, @PathVariable final String id) {

        final User updatedUser = userService.updateUser(userData, Long.parseLong(id));

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable final String id) {

        userService.deleteUser(Long.parseLong(id));

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> updateUser(@Valid @RequestBody final CreatDeposit depositData, @PathVariable final String id) {

        final User depositedUser = userService.createDeposit(depositData, Long.parseLong(id));

        return new ResponseEntity<>(depositedUser, HttpStatus.CREATED);

    }
}
