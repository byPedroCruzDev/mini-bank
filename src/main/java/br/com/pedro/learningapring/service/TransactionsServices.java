package br.com.pedro.learningapring.service;

import br.com.pedro.learningapring.dto.CreateTransactionDto;
import br.com.pedro.learningapring.model.Transaction;
import br.com.pedro.learningapring.model.User;
import br.com.pedro.learningapring.repository.TransactionRepository;
import br.com.pedro.learningapring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TransactionsServices {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionsServices(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public Transaction createTransaction(final CreateTransactionDto transactionData)throws Exception{
        final User foundPayer = userRepository.findById(transactionData.getPayer_id()).orElseThrow(() ->new Exception("User not found"));
        final User foundPayee = userRepository.findById(transactionData.getPayee_id()).orElseThrow(() ->new Exception("User not found"));

        final float payerCurrentBalance = foundPayer.getBalance();
        final float payeeCurrentBalance = foundPayee.getBalance();

        foundPayer.setBalance(payerCurrentBalance - transactionData.getValue());
        foundPayee.setBalance(payeeCurrentBalance + transactionData.getValue());

        final Transaction newTransaction = new Transaction(foundPayer, foundPayee, transactionData.getValue());

        return transactionRepository.save(newTransaction);
    }

    public Transaction retriveTransaction(final long id) throws Exception{
        return transactionRepository.findById(id).orElseThrow(() -> new Exception("Transaction not found"));
    }


}
