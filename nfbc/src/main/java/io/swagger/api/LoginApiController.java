package io.swagger.api;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiParam;
import io.swagger.model.Body;
import io.swagger.model.Body1;
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

    public ResponseEntity<Body1> loginPost(@ApiParam(value = "", required = true) @Valid @RequestBody Body body) {
        try {
            log.info("--trying--");

            if (sessionService.userExist(body.getUsername())) {
                log.info(("username found"));
                int id = sessionService.getUserIdByEmail(body.getUsername());
                if (sessionService.passwordCheck(id, body.getPassword())) {
                    // toegang geven
                    // sessiontoken maken
                    try {
                        Body1 sessionToken = sessionService.getSessionToken(body, id);
                        return new ResponseEntity<Body1>(sessionToken, HttpStatus.OK);
                    } catch (Exception ex) {
                        log.error(ex.getMessage(), ex);
                        return new ResponseEntity<>(HttpStatus.resolve(500));
                    }
                } else {
                    return new ResponseEntity<Body1>(HttpStatus.UNAUTHORIZED);
                }
            } else {
                log.info("user name not found: " + body.getUsername());
                return new ResponseEntity<Body1>(HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            log.info("exceptiont");
            return new ResponseEntity<Body1>(HttpStatus.BAD_REQUEST);
        }
    }
}
