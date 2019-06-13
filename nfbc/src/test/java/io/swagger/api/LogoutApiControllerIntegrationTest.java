package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.model.SessionToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogoutApiControllerIntegrationTest {

    @Autowired
    private LogoutApi api;

    @Test
    public void logoutPostTest() throws Exception {
        SessionToken sessionToken = new SessionToken("c45240d270604010ed1cf5e49a4423f7df74f96d356d051960c0bdf9b84700b1", 3, "User",  "2019-06-13T21:17:07.852", true );
        ResponseEntity<Void> responseEntity = api.logoutPost(sessionToken);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }


}
