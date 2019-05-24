/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Account;
import io.swagger.model.Iban;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-05-24T10:32:59.430Z[GMT]")
@Api(value = "accounts", description = "the accounts API")
public interface AccountsApi {

    @ApiOperation(value = "", nickname = "deleteAccountByIBAN", notes = "delete specified account", response = Account.class, responseContainer = "List", tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Account.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "Id is not correctly formatted"),
        @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/accounts/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<List<Account>> deleteAccountByIBAN(@ApiParam(value = "id of the account you want to (soft)delete",required=true) @PathVariable("iban") String iban);


    @ApiOperation(value = "base for savingsaccount and currentAccounts", nickname = "fetchAccount", notes = "Calling this allows you to fetch the account data", response = Account.class, responseContainer = "List", tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "List of all accounts", response = Account.class, responseContainer = "List"),
        @ApiResponse(code = 400, message = "bad input parameter"),
        @ApiResponse(code = 401, message = "Unauthorized operation") })
    @RequestMapping(value = "/accounts",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<Account>> fetchAccount(@ApiParam(value = "Enter the type of account eg. savings") @Valid @RequestParam(value = "type", required = false) String type);


    @ApiOperation(value = "Get the account related to given id", nickname = "getAccountByIban", notes = "Get the account related to given id", response = Account.class, tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Account corresponding to the given id", response = Account.class),
        @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/accounts/{iban}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Account> getAccountByIban(@ApiParam(value = "IBAN of the account you want to get",required=true) @PathVariable("iban") String iban);


    @ApiOperation(value = "", nickname = "updateAccountByIBAN", notes = "update account by given IBAN", response = Account.class, tags={ "Accounts", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Account.class),
        @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/accounts/{iban}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PATCH)
    ResponseEntity<Account> updateAccountByIBAN(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Account body,@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "iban", required = true) Iban iban2,@ApiParam(value = "",required=true) @PathVariable("iban") String iban);

}
