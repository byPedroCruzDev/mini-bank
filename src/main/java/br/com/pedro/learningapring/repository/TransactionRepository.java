package br.com.pedro.learningapring.repository;

import br.com.pedro.learningapring.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
