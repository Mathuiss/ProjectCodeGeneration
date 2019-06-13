package io.swagger.services;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import javassist.expr.Instanceof;

@Service
public class AccountService {
    private AccountRepository accounts;

    public AccountService(AccountRepository accounts) {
        this.accounts = accounts;

        // ObjectMapper mapper = new ObjectMapper();
        // mapper.enableDefaultTyping();

        // CurrentAccount currAcc = new CurrentAccount(Long.valueOf(2),
        // "NL00INHO0000000003", BigDecimal.valueOf(1000),
        // BigDecimal.valueOf(250), BigDecimal.valueOf(-1000), 4, true, "current");
        // SavingsAccount savAcc = new SavingsAccount(Long.valueOf(2),
        // "NL00INHO0000000004", BigDecimal.valueOf(1000),
        // BigDecimal.valueOf(250), BigDecimal.valueOf(0), 0, true, "savings");

        // List<Account> accountList = new ArrayList<>();
        // accountList.add(currAcc);
        // accountList.add(savAcc);

        // User serializedUser = new User();
        // serializedUser.setAccounts(accountList);

        // String jsonDataString = mapper.writeValueAsString(serializedUser);

        // User deserializedUser = mapper.readValue(jsonDataString, User.class);

        // assertThat(deserializedUser.getAccounts("").get(0),
        // instanceOf(CurrentAccount.class));
        // assertThat(deserializedUser.getAccounts("").get(1),
        // instanceOf(SavingsAccount.class));

        loadOnStartup();
    }

    // load in all accounts from json on startup
    public void loadOnStartup() {
        // determine accountType when reading value

        TypeReference<List<CurrentAccount>> typeReferenceCur = new TypeReference<List<CurrentAccount>>() {
        };

        InputStream inputStream = TypeReference.class.getResourceAsStream("/AccountPersist.json");
        ObjectMapper mapper = new ObjectMapper();

        ArrayList<Account> accountList = new ArrayList<>();
        try {
            List<CurrentAccount> currentAccountList = mapper.readValue(inputStream, typeReferenceCur);
            for (CurrentAccount currAcc : currentAccountList) {
                switch (currAcc.accountType()) {
                case "current":
                    // do nothing
                    break;
                case "savings":
                    accountList.add(new SavingsAccount(currAcc.userId(), currAcc.getIban(), currAcc.getBalance(),
                            currAcc.getTransactionLimit(), currAcc.getAbsoluteLimit(), currAcc.getDailyLimit(),
                            currAcc.getIsActive(), currAcc.accountType()));

                    break;
                default:
                    break;
                }
            }
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
        account.setIsActive(false);

        accounts.save(account);
    }
}