package com.Matheus.GestaoFinanceira.User.controller;

import com.Matheus.GestaoFinanceira.DTOs.user.UserRequest;
import com.Matheus.GestaoFinanceira.DTOs.user.UserResponse;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user/v1")
@PreAuthorize("hasRole('USER')")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable @RequestParam UUID id,
                                                   @RequestBody UserRequest request){
        User user = userService.updateUser(request, id);

        return new ResponseEntity<>(UserResponse.toUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable @RequestParam UUID id){
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
