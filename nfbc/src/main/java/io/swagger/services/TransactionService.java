package io.swagger.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import io.swagger.model.Transaction;
import io.swagger.repositories.TransactionRepository;

@Service
public class TransactionService {
    private TransactionRepository transactions;

    public TransactionService(TransactionRepository transactions) {
        this.transactions = transactions;

        loadOnStartup();
    }

    public void loadOnStartup() {
        // Ingredients for json parser
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Transaction>> typeReference = new TypeReference<List<Transaction>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/TransactionPersist.json");

        try {
            // Parse json into list and save
            List<Transaction> transactionList = mapper.readValue(inputStream, typeReference);
            transactions.saveAll(transactionList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Iterable<Transaction> getTransactions(String datetimestart, String datetimeend, Integer user, String sender,
            String reciever, String accountType, BigDecimal minValue, BigDecimal maxValue, String transactiontype) {

        ArrayList<Transaction> res = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        res.add(new Transaction(0, "sender", "reciever", 0, 24.60, LocalDate.now().format(formatter)));
        return res;

        // // Setting null filters
        // if (datetimestart == null) {
        // datetimestart =
        // LocalDate.MIN.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        // }

        // if (datetimeend == null) {
        // datetimeend =
        // LocalDate.MAX.format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        // }

        // if (minValue == null) {
        // minValue = BigDecimal.valueOf(0);
        // }

        // if (maxValue == null) {
        // maxValue = BigDecimal.valueOf(Double.MAX_VALUE);
        // }

        // Iterable<Transaction> transactionList = transactions.findAll();
        // ArrayList<Transaction> resultList = new ArrayList<Transaction>();

        // for (Transaction transaction : transactionList) {
        // // Check for datetime
        // // if
        // (LocalDate.parse(DateTimeFormatter.ofPattern(transaction.getTimestamp()))
        // // .isBefore(LocalDate.parse(datetimestart))
        // // &&
        // //
        // LocalDate.parse(transaction.getTimestamp()).isAfter(LocalDate.parse(datetimeend)))
        // // {
        // // continue;
        // // }

        // if (LocalDate
        // .parse(transaction.getTimestamp(),
        // java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss"))
        // .isBefore(LocalDate.parse(datetimestart,
        // java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))
        // && LocalDate
        // .parse(transaction.getTimestamp(),
        // java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmssHHmmss"))
        // .isAfter(LocalDate.parse(datetimeend,
        // java.time.format.DateTimeFormatter.ofPattern("yyyyMMddHHmmss")))) {

        // }

        // // Check for user
        // if (user != null) {
        // if (user != transaction.getUser()) {
        // continue;
        // }
        // }

        // // Check for sender
        // if (sender != null) {
        // if (sender != transaction.getSender()) {
        // continue;
        // }
        // }

        // // Check for reciever
        // if (reciever != null) {
        // if (reciever != transaction.getReciever()) {
        // continue;
        // }
        // }

        // // Check for accountType
        // if (accountType != null) {
        // // For now: Add account.
        // // TODO: When AccountRepository is available. Use that.
        // }

        // // Check for values
        // if (transaction.getAmount().doubleValue() < minValue.doubleValue()
        // && transaction.getAmount().doubleValue() > maxValue.doubleValue()) {
        // continue;
        // }

        // if (transactiontype != null) {
        // // For now: Add account.
        // // TODO: When AccountRepository is available. Use that.
        // }

        // resultList.add(transaction);
        // }

        // return resultList;
    }

    public Transaction getTransaction(long id) throws Exception {
        // Execute query
        Optional<Transaction> result = transactions.findById(id);

        // Return if result is ok
        if (result.isPresent()) {
            return result.get();
        }

        throw new Exception("No Transaction Found");
    }

    // Creates a new transaction
    public void createTransaction(Transaction transaction) {
        transactions.save(transaction);
    }
}