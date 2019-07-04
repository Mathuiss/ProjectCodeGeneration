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

import io.swagger.model.Account;
import io.swagger.model.SessionToken;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repositories.AccountRepository;
import io.swagger.repositories.SessionRepository;
import io.swagger.repositories.TransactionRepository;
import io.swagger.repositories.UserRepository;

@Service
public class TransactionService {
    private TransactionRepository transactions;
    private AccountRepository accounts;
    private UserRepository users;
    private SessionRepository sessions;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public TransactionService(TransactionRepository transactions, AccountRepository accounts, UserRepository users,
            SessionRepository sessions) {
        this.transactions = transactions;
        this.accounts = accounts;
        this.users = users;
        this.sessions = sessions;

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

    public Iterable<Transaction> getTransactions(String datetimestart, String datetimeend, Long userId, String sender,
            String reciever, String accountType, BigDecimal minValue, BigDecimal maxValue, String transactiontype,
            String sessionToken) throws Exception {

        // Check to make sure the user only get's his own transactions
        Optional<SessionToken> sessionOpt = sessions.findById(sessionToken);

        if (!sessionOpt.isPresent()) {
            throw new Exception("No session found for id: " + sessionToken);
        }

        SessionToken token = sessionOpt.get();

        Optional<User> userOpt = users.findById(token.getUserId());

        if (!sessionOpt.isPresent()) {
            throw new Exception("User not found for id: " + token.getUserId());
        }

        User userObj = userOpt.get();

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

        // Applying filters
        for (Transaction transaction : transactionList) {
            // Check for datetime
            if (LocalDateTime.parse(transaction.getTimestamp(), formatter)
                    .isBefore(LocalDateTime.parse(datetimestart, formatter))
                    || LocalDateTime.parse(transaction.getTimestamp(), formatter)
                            .isAfter(LocalDateTime.parse(datetimeend, formatter))) {
                continue;
            }

            // Check for user
            if (userId != null) {
                if (userId != transaction.getUserId()) {
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

            if (userObj.getIsEmployee()) {
                resultList.add(transaction);
            } else {
                if (checkAccount(transaction.getSender()).getUserId() == token.getUserId()) {
                    resultList.add(transaction);
                }
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
    public void createTransaction(Transaction transaction) throws Exception {
        transaction.setTimestamp(LocalDateTime.now().format(formatter));
        Account sender = checkAccount(transaction.getSender());
        Account reciever = checkAccount(transaction.getReciever());

        if (sender.getBalance().subtract(transaction.getAmount()).compareTo(BigDecimal.valueOf(0)) == -1) {
            throw new Exception("Balance too low: " + sender.getBalance());
        }

        ArrayList<Account> accountList = new ArrayList<Account>();
        sender.setBalance(sender.getBalance().subtract(transaction.getAmount()));
        reciever.setBalance(reciever.getBalance().add(transaction.getAmount()));
        accountList.add(sender);
        accountList.add(reciever);

        accounts.saveAll(accountList);
        transactions.save(transaction);
    }

    public void withdraw(Transaction transaction) throws Exception {
        transaction.setSender("NL00INHO0000000000");
        createTransaction(transaction);
    }

    public void deposit(Transaction transaction) throws Exception {
        transaction.setReciever("NL00INHO0000000000");
        createTransaction(transaction);
    }

    public boolean hasUserPermission(Transaction transaction, String sessionToken) throws Exception {
        Optional<SessionToken> sessionOpt = sessions.findById(sessionToken);

        if (!sessionOpt.isPresent()) {
            throw new Exception("Session token not found: " + sessionToken);
        }

        SessionToken session = sessionOpt.get();

        Optional<User> userOpt = users.findById(session.getUserId());

        if (!userOpt.isPresent()) {
            throw new Exception("User not found for id: " + session.getUserId());
        }

        User user = userOpt.get();

        if (user.getIsEmployee()) {
            return true;
        }

        Iterable<Account> accountList = accounts.findAccountsByUserId(user.getuserId());

        for (Account acc : accountList) {
            if (acc.getIban().equals(transaction.getSender())) {
                return true;
            }
        }

        return false;
    }

    private Account checkAccount(String iban) throws Exception {
        Optional<Account> acc = accounts.findById(iban);
        if (!acc.isPresent()) {
            throw new Exception("Sender not found: " + iban);
        }

        return acc.get();
    }
}