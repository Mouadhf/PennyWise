package com.pennywise.pw.service;

import com.pennywise.pw.model.User;
import com.pennywise.pw.repository.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(String username,String password) {
        User userExisting = userRepository.findByUsername(username);
        if (userExisting != null) {
            return false;
        }
        User newUser = new User(username,username,password);
        userRepository.save(newUser);
        return true;
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user != null;
    }
}
