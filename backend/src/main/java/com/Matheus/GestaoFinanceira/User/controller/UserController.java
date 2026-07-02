package com.Matheus.GestaoFinanceira.User.controller;

import com.Matheus.GestaoFinanceira.DTOs.user.UserRequest;
import com.Matheus.GestaoFinanceira.DTOs.user.UserResponse;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.service.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1")
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update")
    public ResponseEntity<UserResponse> updateUser(@AuthenticationPrincipal User user,
                                                   @RequestBody @Valid UserRequest request){
        User userS = userService.updateUser(request, user.getId());

        return new ResponseEntity<>(UserResponse.toUser(userS), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal User user){
        userService.deleteUser(user.getId());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
