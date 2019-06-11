/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.model.InlineResponse200;
import io.swagger.model.Transaction;
import io.swagger.model.User;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Api(value = "users", description = "the users API")
public interface UsersApi {

    @ApiOperation(value = "", nickname = "deleteUserById", notes = "delete specified user", response = InlineResponse200.class, authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Users", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = InlineResponse200.class),
            @ApiResponse(code = 400, message = "Id is not correctly formatted"),
            @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/users/{id}", produces = { "application/json" }, method = RequestMethod.DELETE)
    ResponseEntity<InlineResponse200> deleteUserById(
            @ApiParam(value = "id of the user you want to (soft)delete", required = true) @PathVariable("id") Integer id);

    @ApiOperation(value = "", nickname = "getTransactionOfUser", notes = "All transactions of this user", response = Transaction.class, responseContainer = "List", authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of all transactions for this user", response = Transaction.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter"),
            @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/users/transactions", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> getTransactionOfUser(
            @ApiParam(value = "") @Valid @RequestParam(value = "account", required = false) String account);

    @ApiOperation(value = "Gets user corresponding with id", nickname = "getUserById", notes = "Get user corresponding with id", response = User.class, authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Single user corresponding with given id", response = User.class),
            @ApiResponse(code = 400, message = "Id is not correctly formatted"),
            @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/users/{id}", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<User> getUserById(
            @ApiParam(value = "id of the user you want to get", required = true) @PathVariable("id") Integer id);

    @ApiOperation(value = "User", nickname = "getUsers", notes = "Get all users", response = User.class, responseContainer = "List", authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "list of all users", response = User.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter"),
            @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/users", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<Iterable<User>> getUsers(); //iterable veranderd

    @ApiOperation(value = "", nickname = "usersPost", notes = "Create new user", response = User.class, responseContainer = "List", authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Users", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post succesfull", response = User.class, responseContainer = "List"),
            @ApiResponse(code = 400, message = "bad input parameter"),
            @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/users", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)
    ResponseEntity<User> usersPost(@ApiParam(value = "") @Valid @RequestBody User body);

    @ApiOperation(value = "", nickname = "usersPut", notes = "Updated user", response = User.class, authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Users", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Post succesfull", response = User.class),
            @ApiResponse(code = 400, message = "bad input parameter"),
            @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/users", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.PUT)
    ResponseEntity<User> usersPut(@ApiParam(value = "") @Valid @RequestBody User body);

}
