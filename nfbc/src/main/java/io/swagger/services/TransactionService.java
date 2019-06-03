package io.swagger.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.swagger.model.Transaction;
import io.swagger.model.TransactionRepository;

@Service
public class TransactionService {
    private TransactionRepository transactions;

    public TransactionService(TransactionRepository transactions) {
        this.transactions = transactions;
    }

    // Creates a new transaction
    public void createTransaction(Transaction transaction) {
        transactions.save(transaction);
    }
}