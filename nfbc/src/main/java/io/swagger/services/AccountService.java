package io.swagger.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.el.lang.ELArithmetic.BigDecimalDelegate;
import org.omg.CORBA.Current;
import org.springframework.stereotype.Service;

import io.swagger.model.Account;
import io.swagger.model.CurrentAccount;
import io.swagger.model.SavingsAccount;
import io.swagger.model.User;
import io.swagger.repositories.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accounts;

    public AccountService(AccountRepository accounts) {
        this.accounts = accounts;

        loadOnStartup();
    }

    // load in all accounts from json on startup
    public void loadOnStartup() {
        // determine accountType when reading value

        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();

        CurrentAccount currAcc = new CurrentAccount("NL00INHO0000000003", BigDecimal.valueOf(1000),
                BigDecimal.valueOf(250), BigDecimal.valueOf(-1000), 4, true, "current");
        SavingsAccount savAcc = new SavingsAccount("NL00INHO0000000004", BigDecimal.valueOf(1000),
                BigDecimal.valueOf(250), BigDecimal.valueOf(0), 0, true, "savings");
        List<Account> accounts = new ArrayList<>();
        accounts.add(currAcc);
        accounts.add(savAcc);
        User serializedUser = new User();
        serializedUser.setAccounts(accounts);
        // TypeReference<List<Account>> typeReference = new
        // TypeReference<List<Account>>() {
        // };
        // TypeReference<List<CurrentAccount>> typeReferenceCur = new
        // TypeReference<List<CurrentAccount>>() {
        // };
        // TypeReference<List<SavingsAccount>> typeReferenceSav = new
        // TypeReference<List<SavingsAccount>>() {
        // };
        // InputStream inputStream =
        // TypeReference.class.getResourceAsStream("/AccountPersist.json");

        try {
            // List<Account> accountList = mapper.readValue(inputStream, typeReference);
            // List<CurrentAccount> currentAccountList = mapper.readValue(inputStream,
            // typeReferenceCur);
            // List<SavingsAccount> savingsAccountsList = mapper.readValue(inputStream,
            // typeReferenceSav);

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

    public void deleteAccountByIban(String iban) throws Exception {
        Optional<Account> result = accounts.findById(iban);

        // checks if result is not null to avoid NullPointerException
        if (!result.isPresent()) {
            throw new Exception("No account found for iban " + iban);
        }

        Account account = result.get();
        account.setActive(false);

        accounts.save(account);
    }
}