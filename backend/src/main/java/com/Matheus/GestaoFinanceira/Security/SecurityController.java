package com.Matheus.GestaoFinanceira.Security;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Matheus.GestaoFinanceira.DTOs.security.SecurityLoginRequest;
import com.Matheus.GestaoFinanceira.DTOs.security.SecurityRegisterRequest;
import com.Matheus.GestaoFinanceira.DTOs.security.SecurityResponse;
import com.Matheus.GestaoFinanceira.DTOs.user.UserRequest;
import com.Matheus.GestaoFinanceira.DTOs.user.UserResponse;

@RestController("/auth/v1")
public class SecurityController {
    
    private final SecurityService service;

    SecurityController(SecurityService service){
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(SecurityRegisterRequest request){
        var user = service.register(request);
        return new ResponseEntity<>(UserResponse.toUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SecurityResponse> login (SecurityLoginRequest request) {
        service.login(request);
        return ResponseEntity.ok(new SecurityResponse());
    }
}
