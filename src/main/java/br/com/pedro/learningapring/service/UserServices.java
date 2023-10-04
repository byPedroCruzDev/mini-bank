package br.com.pedro.learningapring.service;

import br.com.pedro.learningapring.dto.CreatDeposit;
import br.com.pedro.learningapring.dto.UserDto;
import br.com.pedro.learningapring.exception.AppException;
import br.com.pedro.learningapring.model.User;
import br.com.pedro.learningapring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServices {

    private final UserRepository userRepository;

    public UserServices(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private void checkEmailAndCpf(final UserDto userData){
        if(userRepository.existsUserByCpf(userData.getCpf())){
            throw new AppException("cpfAlreadyInUse", HttpStatus.CONFLICT);
        }
        if (userRepository.existsUserByEmail(userData.getEmail())) {
            throw new AppException("emailAlreadyInUse", HttpStatus.CONFLICT);
        }
    }

    public User createUser(final UserDto userData) {
        checkEmailAndCpf(userData);

        final User user = new User(userData.getName(), userData.getCpf(), userData.getPassword(),
                                    userData.getEmail(), userData.getType());
        return userRepository.save(user);
    }

    public List<User> readUser(){
        return userRepository.findAll();
    }

    public User retriveUser(final long id){
        final User user = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        return user;
    }


    public User updateUser(final UserDto userData, final long id){
        checkEmailAndCpf(userData);
        System.out.println(userData);
        System.out.println("----------------------");

        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        foundUser.setName(userData.getName());
        foundUser.setCpf(userData.getCpf());
        foundUser.setEmail(userData.getEmail());
        foundUser.setPassword(userData.getPassword());
        foundUser.setType(userData.getType());

        return userRepository.save(foundUser);
    }

    public User deleteUser(final long id){
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        userRepository.delete(foundUser);
        return foundUser;
    }


    public User createDeposit(final CreatDeposit depositData, final long id){
        final User foundUser = userRepository.findById(id).orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));

        final float currentBalance = foundUser.getBalance();

        foundUser.setBalance(currentBalance + depositData.getValue());

        return userRepository.save(foundUser);

    }
}