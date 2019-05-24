/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Transaction;
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
@Api(value = "deposit", description = "the deposit API")
public interface DepositApi {

    @ApiOperation(value = "", nickname = "depositPost", notes = "", tags={ "Transactions", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "ok"),
        @ApiResponse(code = 400, message = "Bad input"),
        @ApiResponse(code = 401, message = "Unauthorized") })
    @RequestMapping(value = "/deposit",
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> depositPost(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Transaction body);

}
