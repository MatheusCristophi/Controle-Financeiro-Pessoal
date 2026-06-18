package com.Matheus.GestaoFinanceira.User.service;

import com.Matheus.GestaoFinanceira.Exceptions.User.IdNotFoundException;
import com.Matheus.GestaoFinanceira.User.controller.DTOs.UserRequest;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(UserRequest request){
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(request.password());

        return repository.save(user);
    }

    public Set<User> showAllUser(){
        return repository.readAll();
    }

    public Set<User> showUsersByName(String name){
        return repository.findUserByNameContainingIgnoreCase(name);
    }

    public User updateUser(UserRequest user, UUID id){
        User userSave = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        if (!user.email().isBlank()) {
            userSave.setEmail(user.email());
        }
        if (!user.name().isBlank()){
            userSave.setName(user.name());
        }
        if (!user.password().isBlank()){
            userSave.setPassword(user.password());
        }
            return repository.save(userSave);
    }

    public void deleteUser(UUID id){
        repository.deleteById(id);
    }
}
