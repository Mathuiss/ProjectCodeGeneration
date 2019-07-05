package io.swagger.api;

import java.math.BigDecimal;
import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import io.swagger.services.SecurityService;
import io.swagger.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Transaction;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final HttpServletRequest request;

    private TransactionService service;

    private SecurityService security;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(HttpServletRequest request, TransactionService service, SecurityService security) {
        this.request = request;
        this.service = service;
        this.security = security;
    }

    // Create a new transaction with POST
    public ResponseEntity<Transaction> createTransaction(
            @ApiParam(value = "", required = true) @Valid @RequestBody Transaction body) {

        try {
            if (security.isAllowed(request.getHeader("session"), "customer")) {
                try {
                    if (service.hasUserPermission(body, request.getHeader("session"))) {
                        service.createTransaction(body);
                        log.info("Transaction created: " + body.getTransactionId());
                        return new ResponseEntity<Transaction>(body, HttpStatus.CREATED);
                    } else {
                        log.info("HACKERMAN incoming. User tried to make transaction with wrong account: s:"
                                + body.getSender() + " r: " + body.getReciever());
                        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
                    }
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                log.info("User has no clearence for level customer with session: " + request.getHeader("session"));
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Get list of all transactions with GET and http params
    public ResponseEntity<Iterable<Transaction>> fetchTransaction(
            @ApiParam(value = "") @Valid @RequestParam(value = "datetimestart", required = false) String datetimestart,
            @ApiParam(value = "") @Valid @RequestParam(value = "datetimeend", required = false) String datetimeend,
            @ApiParam(value = "") @Valid @RequestParam(value = "user", required = false) Long userId,
            @ApiParam(value = "") @Valid @RequestParam(value = "sender", required = false) String sender,
            @ApiParam(value = "") @Valid @RequestParam(value = "reciever", required = false) String reciever,
            @ApiParam(value = "") @Valid @RequestParam(value = "accountType", required = false) String accountType,
            @ApiParam(value = "") @Valid @RequestParam(value = "minvalue", required = false) BigDecimal minvalue,
            @ApiParam(value = "") @Valid @RequestParam(value = "maxvalue", required = false) BigDecimal maxvalue,
            @ApiParam(value = "") @Valid @RequestParam(value = "transactiontype", required = false) String transactiontype) {
        try {
            if (security.isAllowed(request.getHeader("session"), "customer")) {
                try {
                    Iterable<Transaction> transactions = service.getTransactions(datetimestart, datetimeend, userId,
                            sender, reciever, accountType, minvalue, maxvalue, transactiontype,
                            request.getHeader("session"));

                    Object[] params = { datetimestart, datetimeend, userId, sender, reciever, accountType, minvalue,
                            maxvalue, transactiontype };
                    log.info(MessageFormat.format("Transactions fetched with args: {0} {1} {2} {3} {4} {5} {6} {7} {8}",
                            params));

                    return new ResponseEntity<Iterable<Transaction>>(transactions, HttpStatus.OK);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Get certain transaction by id
    public ResponseEntity<Transaction> getTransactionById(
            @ApiParam(value = "Id of the transactions you want to get", required = true) @PathVariable("id") Integer id) {
        try {
            if (security.isAllowed(request.getHeader("session"), "customer")) {
                try {
                    Transaction transaction = service.getTransaction(Integer.toUnsignedLong(id));
                    log.info("Transaction fetched with id: " + transaction.getTransactionId());
                    return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);
                } catch (Exception ex) {
                    log.error(ex.getMessage(), ex);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.warn(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
