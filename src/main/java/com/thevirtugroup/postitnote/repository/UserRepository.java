package com.thevirtugroup.postitnote.repository;


import com.thevirtugroup.postitnote.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    static final long DEFAULT_USER_ID = 999L;
    private final User defaultUser;

    public UserRepository() {
        defaultUser = new User();
        defaultUser.withId(DEFAULT_USER_ID);
        defaultUser.withName("Johnny Tango");
        defaultUser.withPassword("password");
        defaultUser.withUsername("user");
    }

    public User findUserByUsername(String username){
        if ("user".equals(username)){
            return defaultUser;
        }
        return null;
    }

    public User findById(Long id){
        if (defaultUser.getId().equals(id)){
            return defaultUser;
        }
        return null;
    }


}
