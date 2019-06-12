package io.swagger.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import io.swagger.model.InlineResponse200;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.services.UserService;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {
    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private UserService service;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UserService service) {
        this.service = service;
    }

    public ResponseEntity<InlineResponse200> deleteUserById(
            @ApiParam(value = "id of the user you want to (soft)delete", required = true) @PathVariable("id") Integer id) {
        try {
            service.DeleteUserById(id);
            return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<InlineResponse200>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Iterable<Transaction>> getTransactionOfUser(
            @ApiParam(value = "", required = true) @PathVariable("id") Integer id,
            @ApiParam(value = "") @Valid @RequestParam(value = "account", required = false) String account) {
        try {
            Iterable<Transaction> transactions = service.GetTransactionOfUser(id);
            return new ResponseEntity<Iterable<Transaction>>(transactions, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<Iterable<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
        }
    }

    public ResponseEntity<User> getUserById(
            @ApiParam(value = "id of the user you want to get", required = true) @PathVariable("id") Integer id) {
        try {
            User user = service.GetUserById(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<User>> getUsers(
            @ApiParam(value = "which users you want to get", required = false) @PathParam("state") String query) {
        try {
            Iterable<User> users = service.GetAllUsers(query);
            return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<User> usersPost(@ApiParam(value = "") @Valid @RequestBody User body) {
        try {
            service.CreateUser(body);
            return new ResponseEntity<User>(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<User>(HttpStatus.CONFLICT);
        }
    }

    public ResponseEntity<User> usersPut(@ApiParam(value = "") @Valid @RequestBody User body) {
        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

}
