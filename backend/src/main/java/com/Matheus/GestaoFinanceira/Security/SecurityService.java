package com.Matheus.GestaoFinanceira.Security;

import org.springframework.stereotype.Service;

import com.Matheus.GestaoFinanceira.User.entity.Roles;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.repository.UserRepository;

@Service
public class SecurityService {
    
    private UserRepository userRepository;

    public SecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String name, String email, String password){
        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(Roles.ROLE_USER);
        
        return userRepository.save(user);
    }

    /*public String login(String email, String password){

    }*/
}
