package br.com.pedro.learningapring.service;
import br.com.pedro.learningapring.dto.CreatDeposit;
import br.com.pedro.learningapring.dto.UserDto;
import br.com.pedro.learningapring.model.User;

import java.util.List;

public interface UserServices {

    User createUser(final UserDto userData);

    List<User> readUsers();

    User retrieveUser(final long id);

    User updateUser(final UserDto userData, final long id);

    void deleteUser(final long id);

    User createDeposit(final CreatDeposit depositData, final long id);

}