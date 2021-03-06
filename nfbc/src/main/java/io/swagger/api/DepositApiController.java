package io.swagger.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Transaction;
import io.swagger.services.TransactionService;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Controller
public class DepositApiController implements DepositApi {

    private static final Logger log = LoggerFactory.getLogger(DepositApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final TransactionService service;

    @org.springframework.beans.factory.annotation.Autowired
    public DepositApiController(ObjectMapper objectMapper, HttpServletRequest request, TransactionService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<Transaction> depositPost(
            @ApiParam(value = "", required = true) @Valid @RequestBody Transaction body) {
        try {
            service.deposit(body);
            log.info("Created deposit");
            return new ResponseEntity<Transaction>(body, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
