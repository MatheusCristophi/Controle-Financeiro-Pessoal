package com.Matheus.GestaoFinanceira.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.Matheus.GestaoFinanceira.Exceptions.security.UsernameNotFoundException;

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
        user.setEmail(email);
        user.setPassword(encoder.encode(password));
        user.setRole(Roles.USER);
        
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException());
    }
}
