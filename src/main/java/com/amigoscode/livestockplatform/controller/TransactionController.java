package com.amigoscode.livestockplatform.controller;

import com.amigoscode.livestockplatform.service.TransactionService;
import com.amigoscodelivestock_platform.api.TransactionsApi;
import com.amigoscodelivestock_platform.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class TransactionController implements TransactionsApi {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public ResponseEntity<List<Transaction>> listTransactions() {

        List<Transaction> list = transactionService.listTransactions();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<Transaction> getTransaction(@PathVariable("transactionId") Integer transactionId) {
        Optional<Transaction> userOptional = transactionService.getTransaction(transactionId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No transaction with id " + transactionId + " found.");
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);

    }

    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction order) {
        Transaction createdUser = transactionService.createTransaction(order);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    public ResponseEntity<Transaction> updateTransaction(@PathVariable("transactionId") Integer transactionId,
            @Valid @RequestBody Transaction order) {
        Optional<Transaction> userOptional = transactionService.getTransaction(transactionId);
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("No transaction with id " + transactionId + " found.");
        }
        Transaction updatedTransaction = transactionService.updateTransaction(transactionId, order);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

    public ResponseEntity<Void> deleteTransaction(@PathVariable("transactionId") Integer transactionId) {
        int status = getTransaction(transactionId).getStatusCode().value();
        if (HttpStatus.NOT_FOUND.value() == status) {
            throw new IllegalArgumentException("The id transaction: " + transactionId + " does not exist.");
        }
        transactionService.deleteTransaction(transactionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
