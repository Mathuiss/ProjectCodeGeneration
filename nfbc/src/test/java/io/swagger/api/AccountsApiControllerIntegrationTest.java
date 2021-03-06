package io.swagger.api;

import io.swagger.model.Account;

import java.math.BigDecimal;

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
        ResponseEntity<Iterable<Account>> responseEntity = api.fetchAccount(null, null, null, null, null, null, null,
                null, null, null, null, null);
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
        Long userId = Long.valueOf(2);
        String iban = "";
        BigDecimal balance = BigDecimal.valueOf(100.00);
        BigDecimal transactionLimit = BigDecimal.valueOf(100.00);
        BigDecimal absoluteLimit = BigDecimal.valueOf(100.00);
        Integer dailyLimit = 1;
        Boolean isActive = true;
        String accountType = "current";

        Account body = new Account(userId, iban, balance, transactionLimit, absoluteLimit, dailyLimit, isActive,
                accountType);
        ResponseEntity<Account> responseEntity = api.createAccount(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void updateAccountByIbanTest() throws Exception {
        Long userId = Long.valueOf(2);
        String iban = "NL00INHO0000000003";
        BigDecimal balance = BigDecimal.valueOf(100.00);
        BigDecimal transactionLimit = BigDecimal.valueOf(100.00);
        BigDecimal absoluteLimit = BigDecimal.valueOf(100.00);
        Integer dailyLimit = 1;
        Boolean isActive = true;
        String accountType = "current";

        Account body = new Account(userId, iban, balance, transactionLimit, absoluteLimit, dailyLimit, isActive,
                accountType);
        ResponseEntity<Account> responseEntity = api.updateAccountByIban(body);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void deleteAccountByIbanTest() throws Exception {
        String iban = "NL00INHO0000000003";
        ResponseEntity<Account> responseEntity = api.deleteAccountByIban(iban);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
