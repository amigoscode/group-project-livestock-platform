package com.amigoscode.livestockplatform.service;

import com.amigoscode.livestockplatform.entity.TransactionsEntity;
import com.amigoscode.livestockplatform.repository.TransactionDao;
import com.amigoscodelivestock_platform.model.Transaction;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    private final TransactionDao transactionDao;

    @Autowired
    public TransactionService(TransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    public List<Transaction> listTransactions() {
        List<TransactionsEntity> deliveryEntities = transactionDao.findAll();
        return deliveryEntities.stream().map(this::buildTransactionModel).toList();
    }

    public Optional<Transaction> getTransaction(Integer id) {
        Optional<Transaction> transaction = this.listTransactions().stream().filter(u -> id == u.getId().intValue())
                .findFirst();
        return transaction;
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        Gson gson = new Gson();
        String tmp = gson.toJson(transaction);
        TransactionsEntity transactionsEntity = transactionDao.save(gson.fromJson(tmp, TransactionsEntity.class));
        return buildTransactionModel(transactionsEntity);
    }

    @Transactional
    public Transaction updateTransaction(Integer id, Transaction transaction) {
        transaction.setId(id);
        Gson gson = new Gson();
        String tmp = gson.toJson(transaction);

        TransactionsEntity transactionsEntity = transactionDao.save(gson.fromJson(tmp, TransactionsEntity.class));

        return buildTransactionModel(transactionsEntity);
    }

    public void deleteTransaction(Integer id) {
        transactionDao.deleteById(id);
    }

    private Transaction buildTransactionModel(TransactionsEntity transactionsEntity) {
        Gson gson = new Gson();
        String tmp = gson.toJson(transactionsEntity);
        return gson.fromJson(tmp, Transaction.class);
    }
}
