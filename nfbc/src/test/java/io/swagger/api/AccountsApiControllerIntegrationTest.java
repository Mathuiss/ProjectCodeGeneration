package io.swagger.api;

import io.swagger.model.Account;
// import io.swagger.model.Iban;
import io.swagger.model.CurrentAccount;

import java.math.BigDecimal;
import java.util.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsApiControllerIntegrationTest {

    @Autowired
    private AccountsApi api;

    @Test
    public void fetchAccountTest() throws Exception {
        String accountType = "";
        ResponseEntity<Iterable<Account>> responseEntity = api.fetchAccount(accountType);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getAccountByIbanTest() throws Exception {
        String iban = "NL00INHO0000000003";
        ResponseEntity<Account> responseEntity = api.getAccountByIban(iban);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void createAccount() throws Exception {
        BigDecimal balance = BigDecimal.valueOf(100.00);
        BigDecimal transactionLimit = BigDecimal.valueOf(100.00);
        BigDecimal absoluteLimit = BigDecimal.valueOf(100.00);
        Integer dailyLimit = 1;
        Boolean active = true;
        String accountType = "current";

        Account body = new CurrentAccount(balance, transactionLimit, absoluteLimit, dailyLimit, active, accountType);
        ResponseEntity<Account> responseEntity = api.createAccount(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void updateAccountByIBANTest() throws Exception {
        String iban = "NL00INHO0000000003";
        BigDecimal balance = BigDecimal.valueOf(100.00);
        BigDecimal transactionLimit = BigDecimal.valueOf(100.00);
        BigDecimal absoluteLimit = BigDecimal.valueOf(100.00);
        Integer dailyLimit = 1;
        Boolean active = true;
        String accountType = "current";

        Account body = new CurrentAccount(balance, transactionLimit, absoluteLimit, dailyLimit, active, accountType);
        ResponseEntity<Account> responseEntity = api.updateAccountByIBAN(body);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deleteAccountByIBANTest() throws Exception {
        String iban = "NL00INHO0000000003";
        ResponseEntity<Account> responseEntity = api.deleteAccountByIBAN(iban);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
