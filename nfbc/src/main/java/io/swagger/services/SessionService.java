package io.swagger.services;

import io.swagger.model.User;
import org.springframework.stereotype.Service;
import io.swagger.repositories.SessionRepositories;

import java.util.Collection;

@Service
public class SessionService {
    //repository aanmaken
    private SessionRepositories sessionRepos;

    private Collection<User> GetAllUsers(){
        return sessionRepos.getAllUsers();
    }

    public boolean userExist(int id){
        return  false;
    }
}
