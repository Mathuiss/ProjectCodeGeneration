package io.swagger.api;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.model.Body;
import io.swagger.model.InlineResponse2001;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApiControllerIntegrationTest {

    @Autowired
    private LoginApi api;

    @Test
    public void loginPostTest() throws Exception {
        Body body = new Body();
        ResponseEntity<InlineResponse2001> responseEntity = api.loginPost(body);
        assertEquals(HttpStatus.NOT_IMPLEMENTED, responseEntity.getStatusCode());
    }

}
