package io.swagger.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

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

    // Get all transactions
    public Iterable<Transaction> getTransactions() {
        return transactions.findAll();
    }

    public Iterable<Transaction> getTransactions(String datetimestart, String datetimeend, Integer user, String sender,
            String reciever, String accountType, BigDecimal minValue, BigDecimal maxValue, String transactiontype) {

        // Setting null filters
        if (datetimestart == null) {
            datetimestart = OffsetDateTime.MIN.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

        if (datetimeend == null) {
            datetimeend = OffsetDateTime.MAX.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }

        if (minValue == null) {
            minValue = BigDecimal.valueOf(0);
        }

        if (maxValue == null) {
            maxValue = BigDecimal.valueOf(Double.MAX_VALUE);
        }

        Iterable<Transaction> transactionList = transactions.findAll();
        ArrayList<Transaction> resultList = new ArrayList<Transaction>();

        for (Transaction transaction : transactionList) {
            // Check for datetime
            if (OffsetDateTime.parse(transaction.getTimestamp(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    .isAfter(OffsetDateTime.parse(datetimestart, DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                    && OffsetDateTime.parse(transaction.getTimestamp(), DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                            .isBefore(OffsetDateTime.parse(datetimeend, DateTimeFormatter.ISO_LOCAL_DATE_TIME))) {
                resultList.add(transaction);
            }

            // Check for user
            if (user != null) {
                if (user == transaction.getUser()) {
                    resultList.add(transaction);
                }
            } else {
                resultList.add(transaction);
            }

            // Check for sender
            if (sender != null) {
                if (sender == transaction.getSender()) {
                    resultList.add(transaction);
                }
            } else {
                resultList.add(transaction);
            }

            // Check for reciever
            if (reciever != null) {
                if (reciever == transaction.getReciever()) {
                    resultList.add(transaction);
                }
            } else {
                resultList.add(transaction);
            }

            // check for accountType
            if (accountType != null) {
                // For now: Add account.
                // TODO: When AccountRepository is available. Use that.
                resultList.add(transaction);
            } else {
                resultList.add(transaction);
            }

            if (transaction.getAmount().doubleValue() > minValue.doubleValue()
                    && transaction.getAmount().doubleValue() < maxValue.doubleValue()) {
                resultList.add(transaction);
            }

            if (transactiontype != null) {
                // For now: Add account.
                // TODO: When AccountRepository is available. Use that.
                resultList.add(transaction);
            } else {
                resultList.add(transaction);
            }
        }

        return resultList;
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