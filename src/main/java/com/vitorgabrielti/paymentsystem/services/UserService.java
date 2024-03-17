package com.vitorgabrielti.paymentsystem.services;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitorgabrielti.paymentsystem.dtos.UserResponseDTO;
import com.vitorgabrielti.paymentsystem.entity.User;
import com.vitorgabrielti.paymentsystem.repositories.UserRepository;
import com.vitorgabrielti.paymentsystem.util.RandomString;

import jakarta.mail.MessagingException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    public UserResponseDTO registerUser(User user) throws UnsupportedEncodingException, MessagingException{
        if(userRepository.findByEmail(user.getEmail()) != null){
            throw new RuntimeException("This email already exists");
        } else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);

            String randomCode = RandomString.generatedRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);
            
            User savedUser = userRepository.save(user);

            UserResponseDTO userResponseDTO = new UserResponseDTO(
                    savedUser.getId(),
                    savedUser.getName(),
                    savedUser.getEmail(),
                    savedUser.getPassword());

            mailService.sendVerificationEmail(user);
            return userResponseDTO;
        }
    }

    public boolean verify(String verificationCode){

        User user = userRepository.findByVerificationCode(verificationCode);

        if(user == null || user.isEnabled()){
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);

            return true;
        }
    }
}
