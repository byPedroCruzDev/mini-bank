package br.com.pedro.learningapring.service.impl;

import br.com.pedro.learningapring.dto.CreateTransactionDto;
import br.com.pedro.learningapring.exception.AppException;
import br.com.pedro.learningapring.model.Transaction;
import br.com.pedro.learningapring.model.User;
import br.com.pedro.learningapring.repository.TransactionRepository;
import br.com.pedro.learningapring.repository.UserRepository;
import br.com.pedro.learningapring.service.TransactionsServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
@Service
public class TransactionServiceImpl implements TransactionsServices {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(final CreateTransactionDto transactionData) {

        final User foundPayer = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() -> new AppException("payerNotFound", HttpStatus.NOT_FOUND));

        if (Objects.equals(foundPayer.getType(), "SELLER")) {
            throw new AppException("invalidUserType", HttpStatus.FORBIDDEN);
        }

        final User foundPayee = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() -> new AppException("payeeNotFound", HttpStatus.NOT_FOUND));

        final float transactionValue = transactionData.getValue();

        final float payerCurrentBalance = foundPayer.getBalance();

        if (payerCurrentBalance < transactionValue) {
            throw new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);
        }

        final float payeeCurrentBalance = foundPayee.getBalance();

        foundPayer.setBalance(payerCurrentBalance - transactionValue);
        foundPayee.setBalance(payeeCurrentBalance + transactionValue);

        final Transaction newTransaction = new Transaction(foundPayer, foundPayee, transactionValue);

        return transactionRepository.save(newTransaction);

    }

    public Transaction retrieveTransaction(final long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new AppException("transactionNotFound", HttpStatus.NOT_FOUND));
    }

}