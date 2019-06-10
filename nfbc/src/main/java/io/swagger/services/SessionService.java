package io.swagger.services;

import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.swagger.repositories.SessionRepositories;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SessionService {
    @Autowired
    private SessionRepositories sessionRepos;

    private Collection<User> GetAllUsers(){
        return sessionRepos.getAllUsers();
    }
    private ArrayList<User> getUserLijst(){
        //ArrayList<User> userLijst = new ArrayList<>(GetAllUsers());
        return new ArrayList<>(GetAllUsers());
    }

    public boolean userExist(String email){

        for (User user : getUserLijst()) {
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return  false;
    }

    public int getUserIdByEmail(String email){
        for (User user : getUserLijst()) {
            if (user.getEmail().equals(email)){
                return user.getId();
            }
        }
        return 0;
    }

    public boolean passwordCheck(int id, String password){
        for (User user : getUserLijst()) {
            if (user.getId().equals(id)){
                if(user.getHash().equals(password)){
                    return true;
                }
            }
        }
        return  false;
    }

    public boolean isEmployee(int id){
        for (User user : getUserLijst()) {
            if (user.getId().equals(id)){
                if(user.isIsEmployee()){
                    return true;
                }
            }
        }
        return  false;
    }
}
