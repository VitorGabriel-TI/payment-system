package com.vitorgabrielti.paymentsystem.controllers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitorgabrielti.paymentsystem.dtos.UserRequestDTO;
import com.vitorgabrielti.paymentsystem.dtos.UserResponseDTO;
import com.vitorgabrielti.paymentsystem.entity.User;
import com.vitorgabrielti.paymentsystem.services.UserService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> registerUser(@RequestBody @Valid UserRequestDTO userRequestDTO) throws UnsupportedEncodingException, MessagingException{
        User user = userRequestDTO.toModel();
        UserResponseDTO userSaved = userService.registerUser(user);
        return ResponseEntity.ok().body(userSaved);
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code){
        if(userService.verify(code)){
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }
}
