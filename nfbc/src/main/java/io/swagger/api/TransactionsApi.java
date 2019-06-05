/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.threeten.bp.OffsetDateTime;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.model.Transaction;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Api(value = "transactions", description = "the transactions API")
public interface TransactionsApi {

    @ApiOperation(value = "Transaction", nickname = "createTransaction", notes = "Calling this allows you to fetch banking Transaction", response = Transaction.class, authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Transactions", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "banking Transaction", response = Transaction.class),
            @ApiResponse(code = 400, message = "bad input parameter") })
    @RequestMapping(value = "/transactions", produces = { "application/json" }, consumes = {
            "application/json" }, method = RequestMethod.POST)
    ResponseEntity<Transaction> createTransaction(
            @ApiParam(value = "", required = true) @Valid @RequestBody Transaction body);

    @ApiOperation(value = "Transaction", nickname = "fetchTransaction", notes = "", response = Transaction.class, responseContainer = "List", authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Transactions", })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get all transactions", response = Transaction.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/transactions", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<List<Transaction>> fetchTransaction(
            @ApiParam(value = "") @Valid @RequestParam(value = "datetimestart", required = false) OffsetDateTime datetimestart,
            @ApiParam(value = "") @Valid @RequestParam(value = "datetimeend", required = false) OffsetDateTime datetimeend,
            @ApiParam(value = "") @Valid @RequestParam(value = "user", required = false) Integer user,
            @ApiParam(value = "") @Valid @RequestParam(value = "sender", required = false) String sender,
            @ApiParam(value = "") @Valid @RequestParam(value = "reciever", required = false) String reciever,
            @ApiParam(value = "") @Valid @RequestParam(value = "accounttype", required = false) String accounttype,
            @ApiParam(value = "") @Valid @RequestParam(value = "minvalue", required = false) BigDecimal minvalue,
            @ApiParam(value = "") @Valid @RequestParam(value = "maxvalue", required = false) BigDecimal maxvalue,
            @ApiParam(value = "") @Valid @RequestParam(value = "transactiontype", required = false) String transactiontype);

    @ApiOperation(value = "", nickname = "getTransactionById", notes = "Get transaction with id", response = Transaction.class, authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Transactions", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Single transaction", response = Transaction.class) })
    @RequestMapping(value = "/transactions/{id}", produces = { "application/json" }, method = RequestMethod.GET)
    ResponseEntity<Transaction> getTransactionById(
            @ApiParam(value = "Id of the transactions you want to get", required = true) @PathVariable("id") Integer id);

}