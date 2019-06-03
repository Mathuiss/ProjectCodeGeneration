package io.swagger.api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.threeten.bp.OffsetDateTime;

import io.swagger.model.Transaction;

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
        OffsetDateTime datetimestart = OffsetDateTime.now();
        OffsetDateTime datetimeend = OffsetDateTime.MAX;
        Integer user = 56;
        String sender = "sender_example";
        String reciever = "reciever_example";
        String accounttype = "accounttype_example";
        BigDecimal minvalue = new BigDecimal(0);
        BigDecimal maxvalue = new BigDecimal(0);
        String transactiontype = "transactiontype_example";

        ResponseEntity<List<Transaction>> responseEntity = api.fetchTransaction(datetimestart, datetimeend, user,
                sender, reciever, accounttype, minvalue, maxvalue, transactiontype);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

    @Test
    public void getTransactionByIdTest() throws Exception {
        Integer id = 56;
        ResponseEntity<Transaction> responseEntity = api.getTransactionById(id);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
