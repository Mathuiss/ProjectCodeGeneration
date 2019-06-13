package io.swagger.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

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
            // We're using the currentAccountList because it's less restricted
            // and Account is abstract
            for (CurrentAccount acc : accountJsonList) {

                // Optional<User> userRes = users.findById(acc.userId());

                // if (!userRes.isPresent()) {
                // throw new Exception("User not found for id: " + acc.userId());
                // }

                // User user = userRes.get();

                switch (acc.accountType()) {
                case "current":
                    CurrentAccount currentAccount = new CurrentAccount(acc.getUserId(), /* user, */ acc.getIban(),
                            acc.getBalance(), acc.getTransactionLimit(), acc.getAbsoluteLimit(), acc.getDailyLimit(),
                            acc.getIsActive(), acc.accountType());

                    accountList.add(currentAccount);
                    break;
                case "savings":
                    SavingsAccount savingsAccount = new SavingsAccount(acc.getUserId(), /* user, */ acc.getIban(),
                            acc.getBalance(), acc.getTransactionLimit(), acc.getAbsoluteLimit(), acc.getDailyLimit(),
                            acc.getIsActive(), acc.accountType());

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
    public Iterable<Account> getAccounts() {
        return accounts.findAll();
    }

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
        Optional<User> userResult = users.findById(body.getUserId());
        if (!userResult.isPresent()) {
            throw new Exception("User not found for id: " + body.getUserId());
        }
        User user = userResult.get().addAccount(body);
        users.save(user);
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
}