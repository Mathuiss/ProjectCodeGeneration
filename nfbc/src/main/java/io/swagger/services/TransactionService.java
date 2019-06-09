package io.swagger.services;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import io.swagger.model.Transaction;
import io.swagger.repositories.TransactionRepository;
import org.threeten.bp.OffsetDateTime;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class TransactionService {
    private TransactionRepository transactions;

    public TransactionService(TransactionRepository transactions) {
        this.transactions = transactions;
    }

    public void LoadOnStartup() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("")))) {
            String line = reader.readLine();
            StringBuilder sb = new StringBuilder();

            while (line != null) {
                sb.append(line);
            }

            JSONParser trans = new JSONParser(sb.toString());

            try {
                for (Object obj : trans.list()) {
                    transactions.save((Transaction) obj);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //Get all transactions
    public Iterable<Transaction> getTransactions() {
//        Transaction transaction = new Transaction();
//        transaction.setAmount(new BigDecimal(24.50));
//        transaction.setReciever("NL00 NFBC 0000000001");
//        transaction.setSender("NL00 NFBC 0000000002");
//        transaction.setTimestamp(OffsetDateTime.now());
//        transaction.setTransactionId(1);
//        transaction.setUser(1);
//
//        ArrayList<Transaction> res = new ArrayList<>();
//        res.add(transaction);
//        return res;

        return transactions.findAll();
    }


    // Creates a new transaction
    public void createTransaction(Transaction transaction) {
        transactions.save(transaction);
    }
}