package io.swagger.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WithdrawApiControllerIntegrationTest {

    @Autowired
    private WithdrawApi api;

    @Test
    public void withdrawPostTest() throws Exception {
        Transaction body = new Transaction();
        ResponseEntity<Transaction> responseEntity = api.withdrawPost(body);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
