package com.vitorgabrielti.paymentsystem.dtos;

import com.vitorgabrielti.paymentsystem.entity.User;

public record UserRequestDTO(String name, String email, String password) {
    public User toModel(){
        return new User(name, email, password);
    }    
}
