package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.User;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void addUser(User user) {
        userRepository.save(user);
    }
    public Iterable<User> allUsers() {

        return userRepository.findAll();
    }
}
