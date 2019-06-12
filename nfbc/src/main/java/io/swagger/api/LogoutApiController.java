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
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Body1;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Controller
public class LogoutApiController implements LogoutApi {

    private static final Logger log = LoggerFactory.getLogger(LogoutApiController.class);
    private SessionService sessionService;

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public LogoutApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> logoutPost(@ApiParam(value = "") @Valid @RequestBody Body1 sessionTokenModel) {
        String accept = request.getHeader("Accept");

        try{
            if(sessionService.isSessionTokenEmpty(sessionTokenModel)){
                return  new ResponseEntity<Void>(HttpStatus.ACCEPTED);
            }else{
                return  new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }
        }
        catch (Exception ex){
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }

}
