package br.com.pedro.learningapring.service;

import br.com.pedro.learningapring.dto.CreatDeposit;
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

    public User retriveUser(final long id) throws Exception{
        final User user = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

        return user;
    }

    public User updateUser(final User userData, final long id) throws Exception{
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

        System.out.println(foundUser);
        System.out.println("---------");
        System.out.println(id);

        foundUser.setName(userData.getName());
        foundUser.setCpf(userData.getCpf());
        foundUser.setEmail(userData.getEmail());
        foundUser.setPassword(userData.getPassword());
        foundUser.setType(userData.getType());

        return userRepository.save(foundUser);
    }

    public User deleteUser(final long id) throws Exception{
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

        userRepository.delete(foundUser);
        return foundUser;
    }


    public User createDeposit(final CreatDeposit depositData, final long id) throws Exception{
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new Exception("User not found"));

        final float currentBalance = foundUser.getBalance();

        foundUser.setBalance(currentBalance + depositData.getValue());

        return userRepository.save(foundUser);

    }
}