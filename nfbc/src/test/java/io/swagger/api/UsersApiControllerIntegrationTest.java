package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.CurrentAccount;
import io.swagger.model.InlineResponse200;
import io.swagger.model.Transaction;
import io.swagger.model.User;

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
public class UsersApiControllerIntegrationTest {

    @Autowired
    private UsersApi api;

    @Test
    public void deleteUserByIdTest() throws Exception {
        Integer id = 2;
        ResponseEntity<InlineResponse200> responseEntity = api.deleteUserById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    //@Test
    //public void getTransactionOfUserTest() throws Exception {
    //Account account = new CurrentAccount();
    //long id = 2;
    //ResponseEntity<Iterable<Transaction>> responseEntity =
    //api.getTransactionOfUser(id, account);
    //assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    //}

    @Test
    public void getUserByIdTest() throws Exception {
        Integer id = 2;
        ResponseEntity<User> responseEntity = api.getUserById(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void getUsersTest() throws Exception {
        ResponseEntity<Iterable<User>> responseEntity = api.getUsers("disabled");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void usersPostTest() throws Exception {
        User body = new User();
        ResponseEntity<User> responseEntity = api.usersPost(body);
        assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
    }

    @Test
    public void usersPutTest() throws Exception {
        User body = new User();
        body.setName("hans");
        ResponseEntity<User> responseEntity = api.usersPut(body);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
