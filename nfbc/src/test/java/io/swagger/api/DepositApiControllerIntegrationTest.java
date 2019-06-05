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
public class DepositApiControllerIntegrationTest {

    @Autowired
    private DepositApi api;

    @Test
    public void depositPostTest() throws Exception {
        Transaction body = new Transaction();
        ResponseEntity<Void> responseEntity = api.depositPost(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}