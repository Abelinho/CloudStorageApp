package com.udacity.jwdnd.course1.cloudstorage.services;

import java.security.SecureRandom;
import java.util.Base64;
import org.springframework.stereotype.Service;
import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Users;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
	private final UserMapper userMapper;
    private final HashService hashService;

  /*  public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }*/

    public boolean isUsernameAvailable(String username) {
        return userMapper.getUser(username) == null;
    }

    public int createUser(Users user) {//call this mehtod during user signup
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);
        String hashedPassword = hashService.getHashedValue(user.getPassword(), encodedSalt);
        return userMapper.insert(new Users(null, user.getUserName(), encodedSalt, hashedPassword, user.getFirstName(), user.getLastName()));
    }

    public Users getUser(String username) {//call this during login/authentication
        return userMapper.getUser(username);
    }	
}
