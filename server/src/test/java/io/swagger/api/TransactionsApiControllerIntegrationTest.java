package io.swagger.api;

import java.math.BigDecimal;
import org.threeten.bp.OffsetDateTime;
import io.swagger.model.Transaction;

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
public class TransactionsApiControllerIntegrationTest {

    @Autowired
    private TransactionsApi api;

    @Test
    public void createTransactionTest() throws Exception {
        Transaction body = new Transaction();
        ResponseEntity<Transaction> responseEntity = api.createTransaction(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void fetchTransactionTest() throws Exception {
//        OffsetDateTime datetimestart = new OffsetDateTime();
//        OffsetDateTime datetimeend = new OffsetDateTime();
//        Integer user = 56;
//        String sender = "sender_example";
//        String reciever = "reciever_example";
//        String accounttype = "accounttype_example";
//        BigDecimal minvalue = new BigDecimal();
//        BigDecimal maxvalue = new BigDecimal();
        String transactiontype = "transactiontype_example";
        //ResponseEntity<List<Transaction>> responseEntity = api.fetchTransaction(datetimestart, datetimeend, user, sender, reciever, accounttype, minvalue, maxvalue, transactiontype);
        ResponseEntity<List<Transaction>> responseEntity = new ResponseEntity<List<Transaction>>(HttpStatus.OK);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getTransactionByIdTest() throws Exception {
        Integer id = 56;
        ResponseEntity<Transaction> responseEntity = api.getTransactionById(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
