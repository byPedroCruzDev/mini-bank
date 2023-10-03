package br.com.pedro.learningapring.service;

import br.com.pedro.learningapring.dto.CreateTransactionDto;
import br.com.pedro.learningapring.exception.AppException;
import br.com.pedro.learningapring.model.Transaction;
import br.com.pedro.learningapring.model.User;
import br.com.pedro.learningapring.repository.TransactionRepository;
import br.com.pedro.learningapring.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServices {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionsServices(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(final CreateTransactionDto transactionData){
        final User foundPayer = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() ->new AppException("User not found", HttpStatus.NOT_FOUND));
        final User foundPayee = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() ->new AppException("User not found", HttpStatus.NOT_FOUND));

        final float payerCurrentBalance = foundPayer.getBalance();
        final float payeeCurrentBalance = foundPayee.getBalance();

        foundPayer.setBalance(payerCurrentBalance - transactionData.getValue());
        foundPayee.setBalance(payeeCurrentBalance + transactionData.getValue());

        final Transaction newTransaction = new Transaction(foundPayer, foundPayee, transactionData.getValue());

        return transactionRepository.save(newTransaction);
    }

    public Transaction retriveTransaction(final long id){
        return transactionRepository.findById(id).orElseThrow(() -> new AppException("Transaction not found", HttpStatus.NOT_FOUND));
    }


}
