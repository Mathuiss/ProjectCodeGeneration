package io.swagger.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiParam;
import io.swagger.model.InlineResponse200;
import io.swagger.model.Transaction;
import io.swagger.model.User;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@RestController
@RequestMapping("/users")
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private UserService service;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request, UserService service) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.service = service;
    }

    public ResponseEntity<InlineResponse200> deleteUserById(
            @ApiParam(value = "id of the user you want to (soft)delete", required = true) @PathVariable("id") Integer id) {
        try
        {
            service.DeleteUserById(id);
            return new ResponseEntity<InlineResponse200>(HttpStatus.OK);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<InlineResponse200>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Transaction>> getTransactionOfUser(
            @ApiParam(value = "") @Valid @RequestParam(value = "account", required = false) String account) {
            //Iterable<>
            return new ResponseEntity<List<Transaction>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> getUserById(
            @ApiParam(value = "id of the user you want to get", required = true) @PathVariable("id") Integer id) {
        try
        {
            User user = service.GetUsersById(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
        catch(Exception ex)
        {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Iterable<User>> getUsers() {
        Iterable<User> users = service.GetAllUsers();
        return new ResponseEntity<Iterable<User>>(users, HttpStatus.OK);
    }

    public ResponseEntity<User> usersPost(@ApiParam(value = "") @Valid @RequestBody User body) {

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> usersPut(@ApiParam(value = "") @Valid @RequestBody User body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

}
