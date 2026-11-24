package com.example.Pokemon.API.REST.service;

import com.example.Pokemon.API.REST.exception.ResourceNotFoundException;
import com.example.Pokemon.API.REST.model.User;
import com.example.Pokemon.API.REST.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Pokemon.API.REST.dto.UserRequest;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public User createUser(UserRequest request){
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("El email ya fue utilizado");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }
}
