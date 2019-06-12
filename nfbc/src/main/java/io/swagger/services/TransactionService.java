package io.swagger.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    // SHIT THAT WERKS:
    // datetimestart
    // datetimeend
    // user
    // sender
    // reciever
    // minvalue
    // maxvalue

    public Iterable<Transaction> getTransactions(String datetimestart, String datetimeend, Integer user, String sender,
            String reciever, String accountType, BigDecimal minValue, BigDecimal maxValue, String transactiontype) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // Setting null filters
        if (datetimestart == null) {
            datetimestart = LocalDateTime.parse("0001-01-01 00:00:00", formatter).format(formatter);
        }

        if (datetimeend == null) {
            datetimeend = LocalDateTime.parse("9999-12-31 23:59:59", formatter).format(formatter);
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
            if (LocalDateTime.parse(transaction.getTimestamp(), formatter)
                    .isBefore(LocalDateTime.parse(datetimestart, formatter))
                    || LocalDateTime.parse(transaction.getTimestamp(), formatter)
                            .isAfter(LocalDateTime.parse(datetimeend, formatter))) {
                continue;
            }

            // Check for user
            if (user != null) {
                if (user != transaction.getUser()) {
                    continue;
                }
            }

            // Check for sender
            if (sender != null) {
                System.out.println(sender);
                System.out.println(transaction.getSender());
                if (!sender.equals(transaction.getSender())) {
                    continue;
                }
            }

            // Check for reciever
            if (reciever != null) {
                if (!reciever.equals(transaction.getReciever())) {
                    continue;
                }
            }

            // Check for accountType
            if (accountType != null) {
                // For now: Add account.
                // TODO: When AccountRepository is available. Use that.
            }

            // Check for values
            if (transaction.getAmount().doubleValue() < minValue.doubleValue()
                    || transaction.getAmount().doubleValue() > maxValue.doubleValue()) {
                continue;
            }

            if (transactiontype != null) {
                // For now: Add account.
                // TODO: When AccountRepository is available. Use that.
            }

            resultList.add(transaction);
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