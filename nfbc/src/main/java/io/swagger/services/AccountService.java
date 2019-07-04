package io.swagger.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder.Case;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.common.util.concurrent.ExecutionError;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.swagger.model.Account;
import io.swagger.model.CurrentAccount;
import io.swagger.model.SavingsAccount;
import io.swagger.model.User;
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
        TypeReference<List<CurrentAccount>> typeReference = new TypeReference<List<CurrentAccount>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/AccountPersist.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        ArrayList<Account> accountList = new ArrayList<>();
        try {
            List<CurrentAccount> accountJsonList = mapper.readValue(inputStream, typeReference);
            // We're using CurrentAccount because it accepts all types of accounts
            // and Account is abstract
            for (CurrentAccount acc : accountJsonList) {

                // Optional<User> userRes = users.findById(acc.userId());

                // if (!userRes.isPresent()) {
                // throw new Exception("User not found for id: " + acc.userId());
                // }

                // User user = userRes.get();

                switch (acc.getAccountType()) {
                case "current":
                    CurrentAccount currentAccount = new CurrentAccount(acc.getUserId(), /* user, */ acc.getIban(),
                            acc.getBalance(), acc.getTransactionLimit(), acc.getAbsoluteLimit(), acc.getDailyLimit(),
                            acc.getIsActive(), acc.getAccountType());

                    accountList.add(currentAccount);
                    break;
                case "savings":
                    SavingsAccount savingsAccount = new SavingsAccount(acc.getUserId(), /* user, */ acc.getIban(),
                            acc.getBalance(), acc.getTransactionLimit(), acc.getAbsoluteLimit(), acc.getDailyLimit(),
                            acc.getIsActive(), acc.getAccountType());

                    accountList.add(savingsAccount);
                    break;
                default:
                    throw new Exception("Unknown accountType");
                }
            }
            accounts.saveAll(accountList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Get all accounts
    public Iterable<Account> getAccounts(String iban, Long userId, String isActive, BigDecimal balance,
            String accountType, Integer dailyLimit, BigDecimal transactionLimit, BigDecimal absoluteLimit) {

        Iterable<Account> accountList = accounts.findAll();
        ArrayList<Account> resultList = new ArrayList<Account>();

        // Apply filters
        for (Account account : accountList) {

            // check if iban is found in the list
            if (iban != null) {
                if (iban != account.getIban()) {
                    continue;
                }
            }

            // check for userId
            if (userId != null) {
                if (userId != account.getUserId()) {
                    continue;
                }
            }

            // check for active
            if (isActive != null) {
                switch (isActive) {
                case "all":
                    continue;
                case "true":
                    if (account.getIsActive() == true) {
                        continue;
                    }
                    break;
                case "false":
                    if (account.getIsActive() == false) {
                        continue;
                    }
                    break;
                default:
                    break;
                }
            }

            // check for account type
            if (accountType != null) {
                if (accountType == "all") {
                    continue;
                }
                if (accountType != account.getAccountType()) {
                    continue;
                }
            }

            // check for daily limit
            if (dailyLimit != null) {
                if (dailyLimit != account.getDailyLimit()) {
                    continue;
                }
            }

            // check for transaction limit
            if (transactionLimit != null) {
                if (transactionLimit != account.getTransactionLimit()) {
                    continue;
                }
            }

            // check for absolute limit
            if (absoluteLimit != null) {
                if (absoluteLimit != account.getAbsoluteLimit()) {
                    continue;
                }
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

            Account savAcc = new SavingsAccount(body.getUserId(), body.getIban(), body.getBalance(),
                    body.getTransactionLimit(), body.getAbsoluteLimit(), body.getDailyLimit(), body.getIsActive(),
                    body.getAccountType());

            accounts.save(savAcc);
        }

        if (body.getAccountType() == "current") {
            Account curAcc = new CurrentAccount(body.getUserId(), body.getIban(), body.getBalance(),
                    body.getTransactionLimit(), body.getAbsoluteLimit(), body.getDailyLimit(), body.getIsActive(),
                    body.getAccountType());

            accounts.save(curAcc);
        }
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