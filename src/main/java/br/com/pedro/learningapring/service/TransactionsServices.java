package br.com.pedro.learningapring.service;

import br.com.pedro.learningapring.dto.CreateTransactionDto;
import br.com.pedro.learningapring.model.Transaction;

public interface TransactionsServices {

    Transaction createTransaction(final CreateTransactionDto transactionData);

    Transaction retrieveTransaction(final long id);

}
