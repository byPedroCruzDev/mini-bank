package br.com.pedro.learningapring.repository;

import br.com.pedro.learningapring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsUserByCpf(final String cpf);
    boolean existsUserByEmail(final String email);
}
