package io.swagger.api;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import io.swagger.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionsApiControllerIntegrationTest {

    @Autowired
    private TransactionsApi api;

    @Test
    public void createTransactionTest() throws Exception {
        Transaction body = new Transaction();
        body.setSender("NL00INHO0472397581");
        body.setReciever("NL00INHO0627184637");
        body.setAmount(new BigDecimal(0));
        body.setTimestamp(OffsetDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        body.setUser(1);

        ResponseEntity<Transaction> responseEntity = api.createTransaction(body);
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void fetchTransactionTest() throws Exception {
        String datetimestart = OffsetDateTime.MIN.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String datetimeend = OffsetDateTime.MAX.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        Integer user = 0;
        String sender = "NL00INHO0000000002";
        String reciever = "NL00INHO0000000001";
        String accounttype = "";
        BigDecimal minvalue = new BigDecimal(0);
        BigDecimal maxvalue = new BigDecimal(Double.MAX_VALUE);
        String transactiontype = "";

        ResponseEntity<Iterable<Transaction>> responseEntity = api.fetchTransaction(datetimestart, datetimeend, user,
                sender, reciever, accounttype, minvalue, maxvalue, transactiontype);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getTransactionByIdTest() throws Exception {
        Integer id = 0;
        ResponseEntity<Transaction> responseEntity = api.getTransactionById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
