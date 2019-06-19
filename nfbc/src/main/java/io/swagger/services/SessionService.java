package io.swagger.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Body;
import io.swagger.model.SessionToken;
import io.swagger.model.User;
import io.swagger.repositories.SessionRepository;
import io.swagger.repositories.UserRepository;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private UserRepository userRepository;
    private static final Logger logger = Logger.getLogger(Swagger2SpringBoot.class.getName());

    public SessionService(SessionRepository sessionRepos, UserRepository userRepository) {
        this.sessionRepository = sessionRepos;
        this.userRepository = userRepository;
    }

    public boolean userExist(String email) {

        for (User user : userRepository.findAll()) {

            if (user.getEmail().equals(email)) {
                logger.info("--- Userexist ---");
                return true;
            }
        }

        return false;
    }

    public long getUserIdByEmail(String email) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                return user.getuserId();
            }
        }
        return 0;
    }

    public boolean passwordCheck(long id, String password) throws NoSuchAlgorithmException {

        for (User user : userRepository.findAll()) {
            if (user.getuserId() == id) {
                logger.info("password is " + user.compareHash(password));
                return user.compareHash(password);
            }
        }
        return false;
    }

    public boolean isUserActive(long id) {
        for (User user : userRepository.findAll()) {
            if (user.getuserId() == id) {
                if (user.getIsActive()) {
                    return true;
                } else {
                    logger.info("user is not active");
                }
            }
        }
        return false;
    }

    public boolean isEmployee(long id) {
        for (User user : userRepository.findAll()) {
            if (user.getuserId() == id) {
                if (user.getIsEmployee()) {
                    logger.info("user is employee");
                    return true;
                }
            }
        }

        return false;
    }

    public SessionToken getSessionToken(long userId) throws Exception {
        SessionToken sessionToken = new SessionToken();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sessionToken.generateSessionToken(System.currentTimeMillis());
        sessionToken.setTimestamp(LocalDateTime.now().format(formatter));
        sessionToken.setActive(true);
        sessionToken.setUserId(userId);

        // Get user from id and check existence
        Optional<User> result = userRepository.findById(userId);
        if (!result.isPresent()) {
            throw new Exception("User not found for id: " + userId);
        }

        if (result.get().getIsEmployee()) {
            sessionToken.setUserRole("employee");
        } else {
            sessionToken.setUserRole("customer");
        }

        sessionRepository.save(sessionToken);
        return sessionToken;
    }

    public void doesSessionTokenExist(String sessionToken) throws Exception {
        Optional<SessionToken> sessionRes = sessionRepository.findById(sessionToken);

        if (!sessionRes.isPresent()) {
            throw new Exception("No session token found for: " + sessionToken);
        }
    }

    public void deActivateSessionToken(SessionToken sessionToken) {

        if (sessionToken.isActive()) {
            sessionToken.setActive(false);

            sessionRepository.save(sessionToken);

            logger.info("sessionToken state " + sessionToken.isActive());
        }
    }
}
