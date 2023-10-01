package br.com.pedro.learningapring.service;

import br.com.pedro.learningapring.model.User;
import br.com.pedro.learningapring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final User userData) {

        final User user = new User(userData.getName(), userData.getCpf(), userData.getPassword(),
                                    userData.getEmail(), userData.getType());
        return userRepository.save(user);
    }

    public List<User> readUser(){
        return userRepository.findAll();
    }


}