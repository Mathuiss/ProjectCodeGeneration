package io.swagger.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.swagger.model.SessionToken;
import io.swagger.model.User;
import io.swagger.repositories.SessionRepository;
import io.swagger.repositories.UserRepository;

@Service
public class SecurityService {
    private SessionRepository sessionRepository;
    private UserRepository userRepository;
    private DateTimeFormatter formatter;

    public SecurityService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public boolean isAllowed(String sessionToken, String role) throws Exception {
        SessionToken session = checkSessionToken(sessionToken);
        User user = checkUser(session.getUserId());

        return checkPermission(session, user, role);
    }

    // Checks if session token exists.
    // Checks if session token is not expired
    private SessionToken checkSessionToken(String sessionToken) throws Exception {
        Optional<SessionToken> sessionRes = sessionRepository.findById(sessionToken);

        if (!sessionRes.isPresent()) {
            throw new Exception("No session token found for: " + sessionToken);
        }

        SessionToken token = sessionRes.get();

        LocalDateTime sessionTime = LocalDateTime.parse(token.getTimestamp(), formatter);
        LocalDateTime threshold = LocalDateTime.now().minusMinutes(15);

        if (sessionTime.isBefore(threshold)) {
            throw new Exception("Session expired threshold: " + threshold + "SessionTime: " + sessionTime);
        }

        return token;
    }

    // Checks if user exists
    private User checkUser(long userId) throws Exception {
        Optional<User> userRes = userRepository.findById(userId);

        if (!userRes.isPresent()) {
            throw new Exception("User not found for id: " + userId);
        }

        return userRes.get();
    }

    // Checks if user has permission to perform operation
    private boolean checkPermission(SessionToken session, User user, String role) {
        if (!user.getIsEmployee() && !role.equals("employee")) {
            return false;
        }

        updateSession(session);
        return true;
    }

    // Updates session with new timestamp
    private void updateSession(SessionToken session) {
        session.setTimestamp(LocalDateTime.now().format(formatter));
        sessionRepository.save(session);
    }
}