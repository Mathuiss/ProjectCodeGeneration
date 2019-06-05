/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.model.Transaction;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Api(value = "deposit", description = "the deposit API")
public interface DepositApi {

    @ApiOperation(value = "", nickname = "depositPost", notes = "", authorizations = {
            @Authorization(value = "ApiKeyAuth") }, tags = { "Transactions", })
    @ApiResponses(value = { @ApiResponse(code = 200, message = "ok"), @ApiResponse(code = 400, message = "Bad input"),
            @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/deposit", consumes = { "application/json" }, method = RequestMethod.POST)
    ResponseEntity<Void> depositPost(@ApiParam(value = "", required = true) @Valid @RequestBody Transaction body);

}