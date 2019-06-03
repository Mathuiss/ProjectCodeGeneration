package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Iban;

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
    public void deleteAccountByIBANTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<List<Account>> responseEntity = api.deleteAccountByIBAN(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void fetchAccountTest() throws Exception {
        String type = "type_example";
        ResponseEntity<List<Account>> responseEntity = api.fetchAccount(type);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getAccountByIbanTest() throws Exception {
        String iban = "iban_example";
        ResponseEntity<Account> responseEntity = api.getAccountByIban(iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void updateAccountByIBANTest() throws Exception {
        Account body = new Account();
        Iban iban2 = new Iban();
        String iban = "iban_example";
        ResponseEntity<Account> responseEntity = api.updateAccountByIBAN(body, iban2, iban);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
