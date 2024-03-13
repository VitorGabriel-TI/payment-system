package com.vitorgabrielti.paymentsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitorgabrielti.paymentsystem.entity.User;
import com.vitorgabrielti.paymentsystem.repositories.UserRepository;
import com.vitorgabrielti.paymentsystem.util.RandomString;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user){
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists");
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generatedRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);
            
            User savedUser = userRepository.save(user);
            return savedUser;
        }
    }
}
