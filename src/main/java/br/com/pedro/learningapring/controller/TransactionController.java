package br.com.pedro.learningapring.controller;

import br.com.pedro.learningapring.dto.CreateTransactionDto;
import br.com.pedro.learningapring.model.Transaction;
import br.com.pedro.learningapring.service.TransactionsServices;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    final TransactionsServices transactionService;

    public TransactionController(TransactionsServices transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@Valid @RequestBody final CreateTransactionDto transactionData) throws Exception {

        final Transaction createdTransaction = transactionService.createTransaction(transactionData);

        return new ResponseEntity<>(createdTransaction, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> retrieveTransaction(@PathVariable final String id) {

        final Transaction transaction = transactionService.retriveTransaction(Long.parseLong(id));

        return new ResponseEntity<>(transaction, HttpStatus.OK);

    }
}