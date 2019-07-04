package io.swagger.api;

import static org.junit.Assert.assertEquals;

import io.swagger.model.SessionToken;
import io.swagger.services.SessionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import io.swagger.model.Body;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApiControllerIntegrationTest {

    @Autowired
    private LoginApi loginApi;
    @Autowired
    private SessionService sessionService;

    @Test
    public void loginPostTest() throws Exception {
        Body body = new Body("test@gmail.com", "test");
        ResponseEntity<SessionToken> responseEntity = loginApi.loginPost(body);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void LoginPostTest() {
        Body body = new Body("test@gmail.com", "test");
        ResponseEntity<SessionToken> responseEntity = loginApi.loginPost(body);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void loginPostUserNotActiveTest() {
        Body body = new Body("actief@gmail.com", "test");
        ResponseEntity<SessionToken> responseEntity = loginApi.loginPost(body);
        assertEquals(HttpStatus.FORBIDDEN, responseEntity.getStatusCode());
    }

    @Test
    public void userExistMethodTest() {
        assertEquals(true, sessionService.userExist("test@gmail.com"));
    }

    @Test
    public void GetUserIdByEmailTest() {
        assertEquals(3, sessionService.getUserIdByEmail("test@gmail.com"));
    }

    @Test
    public void passwordCheckTest() throws Exception {
        assertEquals(true, sessionService.passwordCheck(3, "test"));
    }

    @Test
    public void isEmployeeTest() {
        assertEquals(true, sessionService.isEmployee(3));
    }

    @Test
    public void isUserActiveTest() {
        assertEquals(true, sessionService.isUserActive(3));
    }
}
