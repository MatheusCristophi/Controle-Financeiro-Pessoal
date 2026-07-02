package com.Matheus.GestaoFinanceira.Security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Matheus.GestaoFinanceira.DTOs.security.SecurityLoginRequest;
import com.Matheus.GestaoFinanceira.DTOs.security.SecurityRegisterRequest;
import com.Matheus.GestaoFinanceira.DTOs.security.SecurityResponse;
import com.Matheus.GestaoFinanceira.DTOs.user.UserResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/v1")
public class SecurityController {
    
    private final SecurityService service;

    public SecurityController(SecurityService securityService) {
        this.service = securityService;
    }

    @PostMapping("/registrar")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid SecurityRegisterRequest request){
        var user = service.register(request);
        return new ResponseEntity<>(UserResponse.toUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<SecurityResponse> login (@RequestBody @Valid SecurityLoginRequest request) {
        SecurityResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }
}
