package io.swagger.services;

import io.swagger.model.User;
import io.swagger.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SessionService {
    private SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepos) {
        this.sessionRepository = sessionRepos;
    }

    public Collection<User> getAllUsers(){

        //return sessionRepository.getAllUsers();
        return null;
    }
    private ArrayList<User> getUserList(){
        ArrayList<User> userList = new ArrayList<>(getAllUsers());
        //return new ArrayList<>(getAllUsers());

        return  userList;
    }

    public boolean userExist(String email){

        for (User user : getUserList()) {
            if (user.getEmail().equals(email)){
                return true;
            }
        }
        return  false;
    }

    public int getUserIdByEmail(String email){
        for (User user : getUserList()) {
            if (user.getEmail().equals(email)){
                return user.getId();
            }
        }
        return 0;
    }

    public boolean passwordCheck(int id, String password){
        for (User user : getUserList()) {
            if (user.getId().equals(id)){
                if(user.getHash().equals(password)){
                    return true;
                }
            }
        }
        return  false;
    }

    public boolean isEmployee(int id){
        for (User user : getUserList()) {
            if (user.getId().equals(id)){
                if(user.isIsEmployee()){
                    return true;
                }
            }
        }
        return  false;
    }
}
