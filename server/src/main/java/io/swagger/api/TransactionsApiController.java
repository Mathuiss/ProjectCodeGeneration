package io.swagger.api;

import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import io.swagger.model.Transaction;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-24T10:32:59.430Z[GMT]")
@Controller
public class TransactionsApiController implements TransactionsApi {

    private static final Logger log = LoggerFactory.getLogger(TransactionsApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TransactionsApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Transaction> createTransaction(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Transaction body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Transaction>> fetchTransaction(@ApiParam(value = "") @Valid @RequestParam(value = "datetimestart", required = false) OffsetDateTime datetimestart,@ApiParam(value = "") @Valid @RequestParam(value = "datetimeend", required = false) OffsetDateTime datetimeend,@ApiParam(value = "") @Valid @RequestParam(value = "user", required = false) Integer user,@ApiParam(value = "") @Valid @RequestParam(value = "sender", required = false) String sender,@ApiParam(value = "") @Valid @RequestParam(value = "reciever", required = false) String reciever,@ApiParam(value = "") @Valid @RequestParam(value = "accounttype", required = false) String accounttype,@ApiParam(value = "") @Valid @RequestParam(value = "minvalue", required = false) BigDecimal minvalue,@ApiParam(value = "") @Valid @RequestParam(value = "maxvalue", required = false) BigDecimal maxvalue,@ApiParam(value = "") @Valid @RequestParam(value = "transactiontype", required = false) String transactiontype) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Transaction> getTransactionById(@ApiParam(value = "Id of the transactions you want to get",required=true) @PathVariable("id") Integer id) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Transaction>(HttpStatus.NOT_IMPLEMENTED);
    }

}
