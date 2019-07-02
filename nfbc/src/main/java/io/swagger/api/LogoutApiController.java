package io.swagger.api;

import javax.validation.Valid;

import io.swagger.model.SessionToken;
import io.swagger.services.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
@Controller
public class LogoutApiController implements LogoutApi {

    private static final Logger log = LoggerFactory.getLogger(LogoutApiController.class);
    private SessionService sessionService;

    @org.springframework.beans.factory.annotation.Autowired
    public LogoutApiController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public ResponseEntity<Void> logoutPost(@ApiParam(value = "") @Valid @RequestBody SessionToken sessionToken) {
        // String accept = request.getHeader("Accept");
        log.info("test123");
        try {
            log.info("Try --- methode van logoutpost " + sessionToken.getSessionToken());
            if (sessionService.doesSessionTokenExist(sessionToken.getSessionToken())) {
                sessionService.deActivateSessionToken(sessionToken);
                return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
            }

            // if (sessionToken.isActive() == true) {
            // sessionService.deActivateSessionToken(sessionToken);

            // return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
            // }
            else {
                return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
        }
    }
}
