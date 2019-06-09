package io.swagger.repositories;

import io.swagger.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class SessionRepositories {

    private static Map<Integer, User> users;

    static {
        users = new HashMap<Integer, User>(){
            {
                put(1, new User());
            }
        } ;
    }


    public Collection<User> getAllUsers() {
        return this.users.values();
    }
}
