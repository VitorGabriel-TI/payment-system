package com.vitorgabrielti.paymentsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.vitorgabrielti.paymentsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    UserDetails findByEmail(String email);
    User findByVerificationCode(String verificationCode);
}
