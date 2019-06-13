package io.swagger.services;

import io.swagger.Swagger2SpringBoot;
import io.swagger.model.Body;
import io.swagger.model.Body1;
import io.swagger.model.User;
import io.swagger.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

@Service
public class SessionService {
    private SessionRepository sessionRepository;
    private static final Logger logger = Logger.getLogger(Swagger2SpringBoot.class.getName());
    private List<User> userList = new ArrayList<>();

    public SessionService(SessionRepository sessionRepos) {
        this.sessionRepository = sessionRepos;
    }

    public Collection<User> getAllUsers() {

        // return sessionRepository.getAllUsers();
        return null;
    }

    private List<User> getUserList() {
        // List<User> userList = null;
        // this.userList = new ArrayList<>(getAllUsers());
        logger.info("list maken");

        this.userList.add(new User(26, "naam", "test@gmail.com", "wachtwoord", "straatnaam", "1060PC", 33, " ",
                "0600000000", null, null, false));
        // return new ArrayList<>(getAllUsers());

        return this.userList;
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
        // for (User user : getUserList()) {
        for (User user : this.userList) {
            if (user.getEmail().equals(email)) {
                logger.info("found user id " + user.getId());
                return user.getId();
            }
        }
        return 0;
    }

    public boolean passwordCheck(int id, String password) {
        // for (User user : getUserList()) {
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                if (user.getHash().equals(password)) {
                    logger.info("found user password" + user.getHash());
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmployee(int id) {
        // for (User user : getUserList()) {
        for (User user : this.userList) {
            if (user.getId().equals(id)) {
                if (user.isIsEmployee()) {
                    logger.info("user is eployee");
                    return true;
                }
            }
        }

        return false;
    }

    public Body1 getSessionToken(Body body, int userId) throws NoSuchAlgorithmException {
        Body1 sessionToken = new Body1();
        sessionToken.setSessionToken(System.currentTimeMillis());

        logger.info("--- getSessionToken tot zover ---");

        if (isEmployee(userId)) {
            sessionToken.setUserRole("Employee");
        } else {
            sessionToken.setUserRole("User");
            logger.info("user role is: " + sessionToken.getUserRole());
        }

        return sessionToken;
    }

    public boolean isSessionTokenEmpty(Body1 sessionTokenModel) {
        if (!sessionTokenModel.getSessionToken().isEmpty()) {
            return true;
        }
        return false;
    }
}
