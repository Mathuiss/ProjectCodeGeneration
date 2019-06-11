package io.swagger.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import io.swagger.model.User;
import io.swagger.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    private UserRepository userRepository;

    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;

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

    public void DeleteUserById(Integer id)
    {
        Optional<User> result = userRepository.findById(id);

        if(result.isPresent())
        {
            result.get().setIsActive(false);
        }
    }

    public Iterable<User> GetAllUsers()
    {
        return userRepository.findAll();
    }

    public User GetUsersById(Integer id) throws Exception
    {
        Optional<User> result = userRepository.findById(id);

        if(result.isPresent())
        {
            return result.get();
        }

        throw new Exception("");
    }

    public void CreateUser(User user)
    {
        userRepository.save(user);
    }
}
