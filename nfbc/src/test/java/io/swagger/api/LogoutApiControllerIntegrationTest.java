package io.swagger.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.model.Body1;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogoutApiControllerIntegrationTest {

    @Autowired
    private LogoutApi api;

    @Test
    public void logoutPostTest() throws Exception {
        Body1 body = new Body1();
        ResponseEntity<Void> responseEntity = api.logoutPost(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
