package io.swagger.api;

import java.math.BigDecimal;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.logging.log4j.message.Message;
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
import io.swagger.services.SecurityService;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Controller
public class AccountsApiController implements AccountsApi {

    private static final Logger log = LoggerFactory.getLogger(AccountsApiController.class);

    private final HttpServletRequest request;

    private AccountService service;

    private SecurityService security;

    @org.springframework.beans.factory.annotation.Autowired
    public AccountsApiController(HttpServletRequest request, AccountService service, SecurityService security) {
        this.request = request;
        this.service = service;
        this.security = security;
    }

    public ResponseEntity<Account> deleteAccountByIban(
            @ApiParam(value = "id of the account you want to (soft)delete", required = true) @PathVariable("iban") String iban) {
        try {
            if (security.isAllowed(request.getHeader("session"), "employee")) {
                try {
                    service.deleteAccountByIban(iban);
                    return new ResponseEntity<Account>(HttpStatus.OK);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Iterable<Account>> fetchAccount(
            @ApiParam(value = "") @Valid @RequestParam(value = "iban", required = false) String iban,
            @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Long userId,
            @ApiParam(value = "") @Valid @RequestParam(value = "isActive", required = false) String isActive,
            @ApiParam(value = "") @Valid @RequestParam(value = "balance", required = false) BigDecimal balance,
            @ApiParam(value = "") @Valid @RequestParam(value = "accountType", required = false) String accountType,
            @ApiParam(value = "") @Valid @RequestParam(value = "dailyLimit", required = false) Integer dailyLimit,
            @ApiParam(value = "") @Valid @RequestParam(value = "transactionLimit", required = false) BigDecimal transactionLimit,
            @ApiParam(value = "") @Valid @RequestParam(value = "absoluteLimit", required = false) BigDecimal absoluteLimit) {

        try {
            if (security.isAllowed(request.getHeader("session"), "employee")) {
                try {
                    Iterable<Account> accounts = service.getAccounts(iban, userId, isActive, balance, accountType,
                            dailyLimit, transactionLimit, absoluteLimit);
                    request.getHeader("session");

                    Object[] params = { iban, userId, isActive, accountType, balance, dailyLimit, transactionLimit,
                            absoluteLimit };
                    log.info(MessageFormat.format("Accounts fetched with args: {0} {1} {2} {3} {4} {5} {6} {7}",
                            params));

                    return new ResponseEntity<Iterable<Account>>(accounts, HttpStatus.OK);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<Account> getAccountByIban(
            @ApiParam(value = "Iban of the account you want to get", required = true) @PathVariable("iban") String iban) {
        try {
            if (security.isAllowed(request.getHeader("session"), "customer")) {
                try {
                    Account account = service.getAccount(iban);
                    return new ResponseEntity<Account>(account, HttpStatus.OK);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

    // Gets account with filled in iban and lets hibernate replace entity with same
    // iban
    public ResponseEntity<Account> updateAccountByIban(
            @ApiParam(value = "", required = true) @Valid @RequestBody Account body) {
        try {
            if (security.isAllowed(request.getHeader("session"), "customer")) {
                try {
                    service.saveAccount(body);
                    log.info("Account updated: " + body.getIban());
                    return new ResponseEntity<Account>(HttpStatus.OK);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    return new ResponseEntity<Account>(HttpStatus.CONFLICT);
                }
            } else {
                log.info("User has no clearance to update this account. User:" + body.getUserId());
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Gets account without iban and lets hibernate create one as @Id
    public ResponseEntity<Account> createAccount(
            @ApiParam(value = "", required = true) @Valid @RequestBody Account body) {
        try {
            if (security.isAllowed(request.getHeader("session"), "employee")) {
                try {
                    service.createAccount(body);
                    log.info("Account created: " + body.getIban());
                    return new ResponseEntity<Account>(body, HttpStatus.CREATED);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
                }
            } else {
                log.info("User has no clearance to create new accounts. User:" + body.getUserId());
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
