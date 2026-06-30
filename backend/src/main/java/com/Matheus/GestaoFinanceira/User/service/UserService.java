package com.Matheus.GestaoFinanceira.User.service;

import com.Matheus.GestaoFinanceira.Exceptions.global.IdNotFoundException;
import com.Matheus.GestaoFinanceira.DTOs.user.UserRequest;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Transactional
    public User updateUser(UserRequest user, UUID id){
        User userSave = repository.findById(id)
                .orElseThrow(() -> new IdNotFoundException(id));

        if (!user.email().isBlank()) {
            userSave.setEmail(user.email().toLowerCase());
        }
        if (!user.name().isBlank()){
            userSave.setName(user.name());
        }
        if (!user.password().isBlank()){
            userSave.setPassword(encoder.encode(user.password()));
        }
            return repository.save(userSave);
    }

    @Transactional
    public void deleteUser(UUID id){
        repository.deleteById(id);
    }
}
