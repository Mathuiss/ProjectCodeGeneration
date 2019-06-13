package io.swagger.services;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Body;
import io.swagger.model.SessionToken;
import io.swagger.model.User;
import io.swagger.repositories.SessionRepository;
import io.swagger.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
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

    private Iterable<User> getUserList() {
        return userRepository.findAll();
    }

    public boolean userExist(String email) {
        logger.info("in boolean method userExis " + email);

        for (User user : getUserList()) {
            logger.info("looking in list");
            if (user.getEmail().equals(email)) {
                logger.info("--- Userexist ---");
                return true;
            }
        }

        return false;
    }

    public int getUserIdByEmail(String email) {
        for (User user : userRepository.findAll()) {
            if (user.getEmail().equals(email)) {
                logger.info("found user id " + user.getId());
                return user.getId();
            }
        }
        return 0;
    }

    public boolean passwordCheck(int id, String password) {
        // for (User user : getUserList()) {
        for (User user : userRepository.findAll()) {
            if (user.getId().equals(id)) {
                if (user.getHash().equals(password)) {
                    logger.info("found user password" + user.getHash());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmployee(long id) {
        for (User user : userRepository.findAll()) {
            if (user.getId().equals(id)) {
                if (user.isEmployee()) {
                    logger.info("user is eployee");
                    return true;
                }
            }
        }

        return false;
    }

    public SessionToken getSessionToken(Body body, long userId) throws Exception {
        SessionToken sessionToken = new SessionToken();
        sessionToken.setSessionToken(System.currentTimeMillis());
        sessionToken.setActive(true);
        sessionToken.setUserId(userId);

        logger.info("--- getSessionToken tot zover ---");

        Optional<User> result = userRepository.findById(userId);
        if (!result.isPresent()) {
            throw new Exception("User not found for id: " + userId);
        }


        if (result.get().isEmployee()) {
            sessionToken.setUserRole("Employee");
        } else {
            sessionToken.setUserRole("User");
            logger.info("user role is: " + sessionToken.getUserRole());
        }

        sessionRepository.save(sessionToken);
        return sessionToken;
    }

    public boolean isSessionTokenEmpty(SessionToken sessionTokenModel) {
        if (!sessionTokenModel.getSessionToken().isEmpty()) {
            return true;
        }
        return false;
    }
}
