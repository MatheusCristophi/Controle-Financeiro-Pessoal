package com.Matheus.GestaoFinanceira.Security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Matheus.GestaoFinanceira.DTOs.user.UserRequest;
import com.Matheus.GestaoFinanceira.DTOs.user.UserResponse;

@RestController
public class SecurityController {
    
    private final SecurityService service;

    SecurityController(SecurityService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(UserRequest request){
        var user = service.register(request.name(), request.email(), request.password());
        return new ResponseEntity<>(UserResponse.toUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String>
}
