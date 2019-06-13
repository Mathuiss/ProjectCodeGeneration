package io.swagger.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import io.swagger.model.Transaction;
import io.swagger.model.User;
import io.swagger.repositories.TransactionRepository;
import io.swagger.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.security.cert.PKIXRevocationChecker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    //private TransactionRepository transactionRepository;

    public UserService(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        //this.transactionRepository = transactionRepository;

        loadOnStartup();
    }

    public void loadOnStartup() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<User>> typeReference = new TypeReference<List<User>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/UserPersist.json");

        try {
            List<User> userList = mapper.readValue(inputStream, typeReference);
            userRepository.saveAll(userList);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void DeleteUserById(long id) throws Exception {
        Optional<User> result = userRepository.findById(id);

        if (!result.isPresent()) {
            throw new Exception("User not found for id: " + id);
        }

        User user = result.get();
        user.setIsActive(false);
        userRepository.save(user);

    }

    public Iterable<User> GetAllUsers(String query) throws Exception {
        ArrayList<User> res = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            if (query == null) {
                if (user.getIsActive()) {
                    res.add(user);
                }
            } else {
                switch (query) {
                case "all":
                    res.add(user);
                    break;
                case "disabled":
                    if (!user.getIsActive()) {
                        res.add(user);
                    }
                    break;
                case "employees":
                    if (user.getIsEmployee()) {
                        res.add(user);
                    }
                    break;
                case "notemployees":
                    if (!user.getIsEmployee()) {
                        res.add(user);
                    }
                    break;
                default:
                    throw new Exception("Faulty query: " + query);
                }
            }
        }

        return res;
    }

    public User GetUserById(long id) throws Exception {
        Optional<User> result = userRepository.findById(id);

        if (result.isPresent()) {
            return result.get();
        }

        throw new Exception("");
    }

    public void CreateUser(User user) throws NoSuchAlgorithmException {
        user.generateHash(user.getHash());
        userRepository.save(user);
    }

    public Iterable<Transaction> GetTransactionOfUser(long id) throws Exception {
        try {
            Iterable<Transaction> result = userRepository.findTransactionByUserId(id);
            return result;
        } catch (Exception ex) {
            throw new Exception("");
        }
    }

    public void UpdateUser(User changedUser) throws Exception {
        Long longValue = changedUser.getuserId();
        
        if (longValue == null) {
            throw new Exception("Changed user has no ID. Cannot update.");
        }

        userRepository.save(changedUser);
    }
}
