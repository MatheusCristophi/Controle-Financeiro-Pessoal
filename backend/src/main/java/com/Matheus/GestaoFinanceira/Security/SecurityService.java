package com.Matheus.GestaoFinanceira.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Matheus.GestaoFinanceira.Config.TokenService;
import com.Matheus.GestaoFinanceira.DTOs.security.SecurityLoginRequest;
import com.Matheus.GestaoFinanceira.DTOs.security.SecurityRegisterRequest;
import com.Matheus.GestaoFinanceira.DTOs.security.SecurityResponse;
import com.Matheus.GestaoFinanceira.Exceptions.security.UserNotFoundException;
import com.Matheus.GestaoFinanceira.User.entity.Roles;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.repository.UserRepository;

@Service
public class SecurityService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder encoder;

    public SecurityService(UserRepository userRepository, PasswordEncoder encoder, BCryptPasswordEncoder passwordEncoder, TokenService tokenService, SecurityResponse securityResponse) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
    }

    public User register(SecurityRegisterRequest request) {
        User user = new User();

        user.setName(request.name());
        user.setEmail(request.email().toLowerCase());
        user.setPassword(encoder.encode(request.password()));
        user.setRole(Roles.USER);

        return userRepository.save(user);
    }

    public SecurityResponse login(SecurityLoginRequest request) {
        User user = userRepository.findByEmail(request.email())
        .orElseThrow(() -> new UserNotFoundException());
        if (passwordEncoder.matches(request.password(), user.getPassword())){
            String token = tokenService.generateToken(user);
            return new SecurityResponse(user.getName(), user.getEmail(), token);
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UsernameNotFoundException("email não encontrado"));
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), user.getAuthorities());
    }
}
