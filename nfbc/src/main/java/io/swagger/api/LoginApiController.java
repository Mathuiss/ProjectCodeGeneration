package io.swagger.api;

import javax.validation.Valid;

import io.swagger.model.SessionToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Body;
import io.swagger.services.SessionService;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-03T08:32:11.998Z[GMT]")
// @Controller
@Controller
public class LoginApiController implements LoginApi {
    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);;
    private SessionService sessionService;

    @org.springframework.beans.factory.annotation.Autowired
    public LoginApiController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public ResponseEntity<SessionToken> loginPost(
            @ApiParam(value = "", required = true) @Valid @RequestBody Body body) {
        try {

            if (sessionService.userExist(body.getUsername())) {
                long id = sessionService.getUserIdByEmail(body.getUsername());
                // log.info(String.valueOf(id));
                if (sessionService.passwordCheck(id, body.getPassword())) {
                    if (sessionService.isUserActive(id)) {
                        // toegang geven
                        // sessiontoken maken
                        try {
                            SessionToken sessionToken = sessionService.getSessionToken(id);
                            return new ResponseEntity<SessionToken>(sessionToken, HttpStatus.OK);
                        } catch (Exception ex) {
                            log.error(ex.getMessage(), ex);
                            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                        }
                    } else {
                        return new ResponseEntity<SessionToken>(HttpStatus.UNAUTHORIZED);
                    }

                } else {
                    // return new ResponseEntity<SessionToken>(HttpStatus.UNAUTHORIZED);
                    return new ResponseEntity<SessionToken>(HttpStatus.FORBIDDEN);
                }
            } else {
                log.info("user name not found: " + body.getUsername());
                return new ResponseEntity<SessionToken>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.info("exceptiont");
            return new ResponseEntity<SessionToken>(HttpStatus.BAD_REQUEST);
        }
    }
}
