package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.model.Users;

@Service
public class AuthorizationService {//this is for sign up 

    private UserService userService;

    public AuthorizationService(UserService userService) {
        this.userService = userService;
    }

    public boolean signupUser(Users user) {

        String username = user.getUserName();

        if (!this.userService.isUsernameAvailable(username)) {
            return false;
        }

        this.userService.createUser(user);

        return true;
    }
}
