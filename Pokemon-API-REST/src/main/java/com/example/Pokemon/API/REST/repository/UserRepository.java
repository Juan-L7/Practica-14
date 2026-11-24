package com.example.Pokemon.API.REST.repository;

import com.example.Pokemon.API.REST.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByEmail(String email);


}
