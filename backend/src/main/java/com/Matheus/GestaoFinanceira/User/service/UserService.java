package com.Matheus.GestaoFinanceira.User.service;

import com.Matheus.GestaoFinanceira.Exceptions.global.IdNotFoundException;
import com.Matheus.GestaoFinanceira.DTOs.user.UserRequest;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public User createUser(UserRequest request){
        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(encoder.encode(request.password()));

        return repository.save(user);
    }

    public Set<User> showAllUser(){
        return repository.readAll();
    }

    public User showUserById(UUID id){
        return repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));
    }

    public List<User> showUsersByName(String name){
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
            userSave.setPassword(encoder.encode(user.password()));
        }
            return repository.save(userSave);
    }

    public void deleteUser(UUID id){
        repository.deleteById(id);
    }
}
