package br.com.pedro.learningapring.controller;

import br.com.pedro.learningapring.dto.CreateTransactionDto;
import br.com.pedro.learningapring.model.Transaction;
import br.com.pedro.learningapring.service.TransactionsServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    final TransactionsServices transactionsServices;

    public TransactionController(TransactionsServices transactionsServices) {
        this.transactionsServices = transactionsServices;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody final CreateTransactionDto transactionData) throws Exception{
        final Transaction createdTransaction = transactionsServices.createTransaction(transactionData);


        return new ResponseEntity<Transaction>(createdTransaction, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Transaction> retrieveTransaction(@PathVariable final String id) throws Exception {

        final Transaction transaction = transactionsServices.retriveTransaction(Long.parseLong(id));

        return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);

    }
}