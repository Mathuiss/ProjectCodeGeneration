package io.swagger.services;

import io.swagger.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import io.swagger.model.Transaction;
import io.swagger.repositories.TransactionRepository;

@Service
public class TransactionService {
    private TransactionRepository transactions;
    private TestRepository test;

    public TransactionService(TestRepository test) {
        this.test = test;
    }

    // Creates a new transaction
    public void createTransaction(Transaction transaction) {
        transactions.save(transaction);
    }
}