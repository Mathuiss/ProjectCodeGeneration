/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.8).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import javax.validation.Valid;

import io.swagger.model.SessionToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.model.Body;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Api(value = "login", description = "the login API")
public interface LoginApi {

        @ApiOperation(value = "", nickname = "loginPost", notes = "", response = SessionToken.class, tags = {
                        "Login", })
        @ApiResponses(value = { @ApiResponse(code = 200, message = "ok", response = SessionToken.class),
                        @ApiResponse(code = 400, message = "Abnormal input") })
        @RequestMapping(value = "/login", produces = { "application/json" }, consumes = {
                        "application/json" }, method = RequestMethod.POST)
        ResponseEntity<SessionToken> loginPost(@ApiParam(value = "", required = true) @Valid @RequestBody Body body);

}
