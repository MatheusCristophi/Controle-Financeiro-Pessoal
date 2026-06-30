package com.Matheus.GestaoFinanceira.Security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Matheus.GestaoFinanceira.User.entity.Roles;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.repository.UserRepository;

@Service
public class SecurityService implements UserDetailsService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public SecurityService(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User register(String name, String email, String password){
        User user = new User();

        user.setName(name);
        user.setEmail(email.toLowerCase());
        user.setPassword(encoder.encode(password));
        user.setRole(Roles.USER);
        
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email.toLowerCase())
        .orElseThrow(() -> new UsernameNotFoundException("email não encontrado"));
        return new org.springframework.security.core.userdetails.User(
            user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
