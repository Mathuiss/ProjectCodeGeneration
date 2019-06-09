package io.swagger.api;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.services.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.threeten.bp.OffsetDateTime;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Transaction;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private TransactionService service;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request, TransactionService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    //Create a new transaction with POST
    public ResponseEntity<Transaction> createTransaction(
            @ApiParam(value = "", required = true) @Valid @RequestBody Transaction body) {
        String accept = request.getHeader("Accept");

        try {
            service.createTransaction(body);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        return new ResponseEntity<Transaction>(HttpStatus.CREATED);
    }

    //Get list of all transactions with GET and http params
    public ResponseEntity<Iterable<Transaction>> fetchTransaction(
            @ApiParam(value = "") @Valid @RequestParam(value = "datetimestart", required = false) OffsetDateTime datetimestart,
            @ApiParam(value = "") @Valid @RequestParam(value = "datetimeend", required = false) OffsetDateTime datetimeend,
            @ApiParam(value = "") @Valid @RequestParam(value = "user", required = false) Integer user,
            @ApiParam(value = "") @Valid @RequestParam(value = "sender", required = false) String sender,
            @ApiParam(value = "") @Valid @RequestParam(value = "reciever", required = false) String reciever,
            @ApiParam(value = "") @Valid @RequestParam(value = "accounttype", required = false) String accounttype,
            @ApiParam(value = "") @Valid @RequestParam(value = "minvalue", required = false) BigDecimal minvalue,
            @ApiParam(value = "") @Valid @RequestParam(value = "maxvalue", required = false) BigDecimal maxvalue,
            @ApiParam(value = "") @Valid @RequestParam(value = "transactiontype", required = false) String transactiontype) {

        Iterable<Transaction> transactions = service.getTransactions();
        return new ResponseEntity<Iterable<Transaction>>(transactions, HttpStatus.OK);
    }

    //Get certain transaction by id
    public ResponseEntity<Transaction> getTransactionById(
            @ApiParam(value = "Id of the transactions you want to get", required = true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

}
