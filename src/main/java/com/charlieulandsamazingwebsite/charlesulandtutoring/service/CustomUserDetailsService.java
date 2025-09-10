package com.charlieulandsamazingwebsite.charlesulandtutoring.service;

import com.charlieulandsamazingwebsite.charlesulandtutoring.model.AppUser;
import com.charlieulandsamazingwebsite.charlesulandtutoring.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("AppUser not found"));

        return new User(user.getEmail(), user.getHashPassword(), List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole())));
    }
}
