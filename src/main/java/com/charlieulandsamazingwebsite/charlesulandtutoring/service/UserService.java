package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.exception.AppUserAlreadyExistsException;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.AppUser;
import com.charlieulandsamazingwebsite.charlesulandtutoring.model.UserRegistrationDto;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Iterable<AppUser> allUsers() {

        return userRepository.findAll();
    }

    public void registerNewUser(UserRegistrationDto dto) {
        if(userRepository.existsByEmail(dto.getEmail())) {
            throw new AppUserAlreadyExistsException();
        }

        AppUser user = new AppUser();

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setHashPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole("STUDENT");
        userRepository.save(user);

    }


}
