package io.swagger.services;

import java.io.Console;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.model.Account;
import io.swagger.model.User;
import io.swagger.models.auth.In;
import io.swagger.repositories.AccountRepository;
import io.swagger.repositories.UserRepository;

@Service
// @DependsOn("loadUsers")
public class AccountService {
    private AccountRepository accounts;
    private UserRepository users;

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountRepository accounts, UserRepository users) {
        this.accounts = accounts;
        this.users = users;

        // loadOnStartup();
    }

    // load in all accounts from json on startup
    @Bean("loadAccounts")
    public void loadOnStartup() {
        TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/AccountPersist.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        ArrayList<Account> accountList = new ArrayList<>();
        try {
            List<Account> accountJsonList = mapper.readValue(inputStream, typeReference);
            for (Account acc : accountJsonList) {

                Account account = new Account(acc.getUserId(), /* user, */ acc.getIban(), acc.getBalance(),
                        acc.getTransactionLimit(), acc.getAbsoluteLimit(), acc.getDailyLimit(), acc.getIsActive(),
                        acc.getAccountType());

                accountList.add(account);
            }
            accounts.saveAll(accountList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Get all accounts
    public Iterable<Account> getAccounts(String iban, Long userId, Boolean isActive, BigDecimal balanceMin,
            BigDecimal balanceMax, String accountType, Integer dailyLimitMin, Integer dailyLimitMax,
            BigDecimal transactionLimitMin, BigDecimal transactionLimitMax, BigDecimal absoluteLimitMin,
            BigDecimal absoluteLimitMax) {

        Iterable<Account> accountList = accounts.findAll();
        ArrayList<Account> resultList = new ArrayList<Account>();

        if (balanceMin == null) {
            balanceMin = BigDecimal.valueOf(-Double.MAX_VALUE);
        }

        if (balanceMax == null) {
            balanceMax = BigDecimal.valueOf(Double.MAX_VALUE);
        }

        if (dailyLimitMin == null) {
            dailyLimitMin = 0;
        }

        if (dailyLimitMax == null) {
            dailyLimitMax = Integer.MAX_VALUE;
        }

        if (transactionLimitMin == null) {
            transactionLimitMin = BigDecimal.valueOf(0);
        }

        if (transactionLimitMax == null) {
            transactionLimitMax = BigDecimal.valueOf(Double.MAX_VALUE);
        }

        if (absoluteLimitMin == null) {
            absoluteLimitMin = BigDecimal.valueOf(-Double.MAX_VALUE);
        }

        if (absoluteLimitMax == null) {
            absoluteLimitMax = BigDecimal.valueOf(Double.MAX_VALUE);
        }

        // Apply filters
        for (Account account : accountList) {

            // check if iban is found in the list
            if (iban != null) {
                if (!iban.equals(account.getIban())) {
                    continue;
                }
            }

            // check for userId
            if (userId != null) {
                if (!userId.equals(account.getUserId())) {
                    continue;
                }
            }

            // check for account type
            if (accountType != null) {
                if (!accountType.equals("all")) {
                    if (!accountType.equals(account.getAccountType())) {
                        continue;
                    }
                }
            }

            // check for active
            if (isActive != null) {
                if (!isActive.equals(account.getIsActive())) {
                    continue;
                }
            }

            // check for balance
            if (account.getBalance().doubleValue() <= balanceMin.doubleValue()
                    || account.getBalance().doubleValue() >= balanceMax.doubleValue()) {
                continue;
            }

            // check for absolute limit
            if (account.getAbsoluteLimit().doubleValue() <= absoluteLimitMin.doubleValue()
                    || account.getAbsoluteLimit().doubleValue() >= absoluteLimitMax.doubleValue()) {
                continue;
            }

            // check for daily limit
            if (account.getDailyLimit() <= dailyLimitMin || account.getDailyLimit() >= dailyLimitMax) {
                continue;
            }

            // check for transaction limit
            if (account.getTransactionLimit().doubleValue() <= transactionLimitMin.doubleValue()
                    || account.getTransactionLimit().doubleValue() >= transactionLimitMax.doubleValue()) {
                continue;
            }

            resultList.add(account);
        }

        return resultList;

    }

    // Redundant
    public Account getAccount(String iban) throws Exception {
        Optional<Account> result = accounts.findById(iban);

        if (result.isPresent()) {
            return result.get();
        }

        throw new Exception("No Account found");
    }

    // Creates or updates an account
    public void saveAccount(Account account) {
        accounts.save(account);
    }

    public void createAccount(Account body) throws Exception {
        body.setIban();

        Optional<User> result = users.findById(body.getUserId());

        // checks if result is not null to avoid NullPointerException
        if (!result.isPresent()) {
            throw new Exception("No user found for userId " + body.getUserId());
        }

        if (body.getAccountType() == "savings") {
            BigDecimal big = new BigDecimal("0");
            body.setAbsoluteLimit(big);
        }

        accounts.save(body);
        // Optional<User> userResult = users.findById(body.getUserId());
        // if (!userResult.isPresent()) {
        // throw new Exception("User not found for id: " + body.getUserId());
        // }
        // User user = userResult.get().addAccount(body);
        // users.save(user);
    }

    public void deleteAccountByIban(String iban) throws Exception {
        Optional<Account> result = accounts.findById(iban);

        // checks if result is not null to avoid NullPointerException
        if (!result.isPresent()) {
            throw new Exception("No account found for iban " + iban);
        }

        Account account = result.get();
        account.setIsActive(false);

        accounts.save(account);
    }

    public boolean doesUserExist(Long userId) throws Exception {

        return true;
    }
}