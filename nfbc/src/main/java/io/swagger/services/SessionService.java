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
        User user = userRepository.findUserByEmail(email);

        if (user != null) {
            return true;
        }
        return false;
    }

    public long getUserIdByEmail(String email) {
        User user = userRepository.findUserByEmail(email);

        if (user != null) {
            return user.getuserId();
        }
        return 0;
    }

    public boolean passwordCheck(long id, String password) throws NoSuchAlgorithmException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            logger.info("password is " + user.get().compareHash(password));
            return user.get().compareHash(password);
        }
        return false;
    }

    public boolean isUserActive(long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            if (user.get().getIsActive()) {
                return true;
            } else {
                logger.info("user is not active");
            }
        }

        return false;
    }

    public boolean isEmployee(long id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()) {
            if (user.get().getIsEmployee()) {
                logger.info("user is employee");
                return true;
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

    public boolean doesSessionTokenExist(String sessionToken) {
        return sessionRepository.findById(sessionToken).isPresent();
    }

    public boolean isSessionTokenStillActive(String sessionToken) {
        Optional<SessionToken> result = sessionRepository.findById(sessionToken);
        SessionToken dbsSessionToken = result.get();

        logger.info("is active " + dbsSessionToken.isActive());
        if (dbsSessionToken.isActive()) {
            return true;
        }
        return false;
    }

    public void deActivateSessionToken(SessionToken sessionToken) {
        logger.info("sessionToken state: " + sessionToken.isActive());
        if (sessionToken.isActive()) {
            sessionToken.setActive(false);
        }

        sessionRepository.save(sessionToken);
        logger.info("sessionToken state: " + sessionToken.isActive());
    }
}
