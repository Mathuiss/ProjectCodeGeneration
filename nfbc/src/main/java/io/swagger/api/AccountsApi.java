/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import java.math.BigDecimal;

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
import io.swagger.model.Account;
// import io.swagger.model.Iban;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Api(value = "accounts", description = "the accounts API")
public interface AccountsApi {

        @ApiOperation(value = "Account", nickname = "deleteAccountByIban", notes = "delete specified account", response = Account.class, responseContainer = "List", authorizations = {
                        @Authorization(value = "ApiKeyAuth") }, tags = { "Accounts", })
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "OK", response = Account.class, responseContainer = "List"),
                        @ApiResponse(code = 400, message = "Id is not correctly formatted"),
                        @ApiResponse(code = 401, message = "Unauthorized") })
        @RequestMapping(value = "/accounts/{iban}", produces = { "application/json" }, method = RequestMethod.DELETE)
        ResponseEntity<Account> deleteAccountByIban(
                        @ApiParam(value = "id of the account you want to (soft)delete", required = true) @PathVariable("iban") String iban);

        @ApiOperation(value = "base for savingsaccount and currentAccounts", nickname = "fetchAccount", notes = "Calling this allows you to fetch the account data", response = Account.class, responseContainer = "List", authorizations = {
                        @Authorization(value = "ApiKeyAuth") }, tags = { "Accounts", })
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "List of all accounts", response = Account.class, responseContainer = "List"),
                        @ApiResponse(code = 400, message = "bad input parameter"),
                        @ApiResponse(code = 401, message = "Unauthorized operation") })
        @RequestMapping(value = "/accounts", produces = { "application/json" }, method = RequestMethod.GET)
        ResponseEntity<Iterable<Account>> fetchAccount(
                        @ApiParam(value = "") @Valid @RequestParam(value = "iban", required = false) String iban,
                        @ApiParam(value = "") @Valid @RequestParam(value = "userId", required = false) Long userId,
                        @ApiParam(value = "") @Valid @RequestParam(value = "isActive", required = false) String isActive,
                        @ApiParam(value = "") @Valid @RequestParam(value = "balance", required = false) BigDecimal balance,
                        @ApiParam(value = "") @Valid @RequestParam(value = "accountType", required = false) String accountType,
                        @ApiParam(value = "") @Valid @RequestParam(value = "dailyLimit", required = false) Integer dailyLimit,
                        @ApiParam(value = "") @Valid @RequestParam(value = "transactionLimit", required = false) BigDecimal transactionLimit,
                        @ApiParam(value = "") @Valid @RequestParam(value = "absoluteLimit", required = false) BigDecimal absoluteLimit);

        @ApiOperation(value = "Get the account related to given id", nickname = "getAccountByIban", notes = "Get the account related to given id", response = Account.class, authorizations = {
                        @Authorization(value = "ApiKeyAuth") }, tags = { "Accounts", })
        @ApiResponses(value = {
                        @ApiResponse(code = 200, message = "Account corresponding to the given id", response = Account.class),
                        @ApiResponse(code = 401, message = "Unauthorized") })
        @RequestMapping(value = "/accounts/{iban}", produces = { "application/json" }, method = RequestMethod.GET)
        ResponseEntity<Account> getAccountByIban(
                        @ApiParam(value = "Iban of the account you want to get", required = true) @PathVariable("iban") String iban);

        @ApiOperation(value = "Account", nickname = "updateAccountByIban", notes = "update account by given Iban", response = Account.class, authorizations = {
                        @Authorization(value = "ApiKeyAuth") }, tags = { "Accounts", })
        @ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Account.class),
                        @ApiResponse(code = 401, message = "Unauthorized") })
        @RequestMapping(value = "/accounts/{iban}", produces = { "application/json" }, consumes = {
                        "application/json" }, method = RequestMethod.PUT)
        ResponseEntity<Account> updateAccountByIban(
                        @ApiParam(value = "", required = true) @Valid @RequestBody Account body);

        @ApiOperation(value = "Account", nickname = "createAccount", notes = "create new account", response = Account.class, authorizations = {
                        @Authorization(value = "ApiKeyAuth") }, tags = { "Accounts", })
        @ApiResponses(value = { @ApiResponse(code = 201, message = "CREATED", response = Account.class),
                        @ApiResponse(code = 401, message = "Unauthorized") })
        @RequestMapping(value = "/accounts/", produces = { "application/json" }, consumes = {
                        "application/json" }, method = RequestMethod.POST)
        ResponseEntity<Account> createAccount(@ApiParam(value = "", required = true) @Valid @RequestBody Account body);

}
