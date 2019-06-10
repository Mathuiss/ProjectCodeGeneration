package io.swagger.api;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Body;
import io.swagger.model.InlineResponse2001;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
//@Controller
@Controller
public class LoginApiController implements LoginApi {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private SessionService sessionService;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(ObjectMapper objectMapper, HttpServletRequest request, SessionService sessionService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.sessionService = sessionService;
    }

    public ResponseEntity<InlineResponse2001> loginPost(
            @ApiParam(value = "", required = true) @PathVariable("Email") String Email, @PathVariable("password") String password) { ////@Valid @RequestBody Body body)
        String accept = request.getHeader("Accept");

        try{
            if(sessionService.userExist(Email)){
                int id = sessionService.getUserIdByEmail(Email);
                if(sessionService.passwordCheck(id, password)){
                    //toegang geven

                    return new ResponseEntity<InlineResponse2001>(HttpStatus.ACCEPTED);
                }
                else{
                    return new ResponseEntity<InlineResponse2001>(HttpStatus.FAILED_DEPENDENCY);
                }
            }
            else{
                return new ResponseEntity<InlineResponse2001>(HttpStatus.FAILED_DEPENDENCY);
            }

        }
        catch (Exception ex){
            return new ResponseEntity<InlineResponse2001>(HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
