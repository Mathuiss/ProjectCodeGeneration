package io.swagger.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Account;
import io.swagger.services.AccountService;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private AccountService service;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(AccountService service) {
        this.service = service;
    }

    public ResponseEntity<Account> deleteAccountByIban(
            @ApiParam(value = "id of the account you want to (soft)delete", required = true) @PathVariable("iban") String iban) {
        try {
            service.deleteAccountByIban(iban);
            return new ResponseEntity<Account>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Iterable<Account>> fetchAccount(
            @ApiParam(value = "Enter the type of account eg. savings") @Valid @RequestParam(value = "accounttype", required = false) String AccountType) {
        try {
            Iterable<Account> accounts = service.getAccounts();
            return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Account> getAccountByIban(
            @ApiParam(value = "Iban of the account you want to get", required = true) @PathVariable("iban") String iban) {
        try {
            Account account = service.getAccount(iban);
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }

    }

    // Gets account with filled in iban and lets hibernate replace entity with same
    // iban
    public ResponseEntity<Account> updateAccountByIban(
            @ApiParam(value = "", required = true) @Valid @RequestBody Account body) {
        try {
            service.saveAccount(body);
            return new ResponseEntity<Account>(HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Account>(HttpStatus.CONFLICT);
        }
    }

    // Gets account without iban and lets hibernate create one as @Id
    public ResponseEntity<Account> createAccount(
            @ApiParam(value = "", required = true) @Valid @RequestBody Account body) {
        try {
            service.saveAccount(body);
            return new ResponseEntity<Account>(HttpStatus.CREATED);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<Account>(HttpStatus.CONFLICT);
        }
    }
}
