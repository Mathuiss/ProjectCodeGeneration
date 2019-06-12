package io.swagger.services;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.stereotype.Service;

import io.swagger.model.Account;
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
        ObjectMapper mapper = new ObjectMapper();
        mapper.enableDefaultTyping();
        // mapper.enableDefaultTyping(ObjectMapper.DefaultTyping applicability,
        // JsonTypeInfo.As includeAs);

        // TK
        // might be necessary to prevent JSON 0.001 to parse as Double instead of
        // BigDecimal
        // mapper.enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS);

        TypeReference<List<Account>> typeReference = new TypeReference<List<Account>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/AccountPersist.json");

        try {
            List<Account> accountList = mapper.readValue(inputStream, typeReference);
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

    // Creates a new account
    public void createAccount(Account body) {
        accounts.save(body);
    }

    // duplicate of createAccount, to make it more clear
    // 'save' has a built-in update if an id is given
    public void updateAccountByIban(Account body) {
        accounts.save(body);
    }
}