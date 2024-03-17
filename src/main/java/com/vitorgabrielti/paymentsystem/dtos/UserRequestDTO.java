package com.vitorgabrielti.paymentsystem.dtos;

import com.vitorgabrielti.paymentsystem.entity.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
    
    @NotNull
    @NotBlank
    String name, 

    @NotNull
    @NotBlank
    @Email
    String email, 
    
    @NotNull
    @NotBlank
    @Size(min = 8, message = "A senha deve conter no minimo 8 caracteres")
    String password) {
    public User toModel(){
        return new User(name, email, password);
    }    
}
