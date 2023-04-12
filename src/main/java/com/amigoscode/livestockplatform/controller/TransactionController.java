package com.amigoscode.livestockplatform.controller;

import com.amigoscodelivestock_platform.api.TransactionsApi;
import com.amigoscodelivestock_platform.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("${openapi.swaggerPetstoreOpenAPI30.base-path:/api/v1}")
public class TransactionController implements TransactionsApi {

    public ResponseEntity<List<Transaction>> listTransactions() {
        Transaction transaction = new Transaction();
        List<Transaction> list = new ArrayList<>();
        list.add(transaction);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
