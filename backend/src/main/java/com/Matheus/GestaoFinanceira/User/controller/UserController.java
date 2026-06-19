package com.Matheus.GestaoFinanceira.User.controller;

import com.Matheus.GestaoFinanceira.User.controller.DTOs.UserRequest;
import com.Matheus.GestaoFinanceira.User.controller.DTOs.UserResponse;
import com.Matheus.GestaoFinanceira.User.entity.User;
import com.Matheus.GestaoFinanceira.User.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController("/user/v1")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<Set<UserResponse>> getAllUsers(){
        Set<UserResponse> allUsers = userService.showAllUser()
                .stream()
                .map(UserResponse::toUser)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/finduser/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable @RequestParam UUID id){
        User user = userService.showUserById(id);
        return ResponseEntity.ok(UserResponse.toUser(user));
    }

    @GetMapping("/name")
    public ResponseEntity<List<UserResponse>> getUserWithName(@PathVariable @RequestBody String name){
        List<UserResponse> users = userService.showUsersByName(name)
                .stream()
                .map(UserResponse::toUser)
                .toList();

        return ResponseEntity.ok(users);
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request){
        User user = userService.createUser(request);

        return new ResponseEntity<>(UserResponse.toUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> updateUser(@PathVariable @RequestParam UUID id,
                                                   @RequestBody UserRequest request){
        User user = userService.updateUser(request, id);

        return new ResponseEntity<>(UserResponse.toUser(user), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUser(@PathVariable @RequestParam UUID id){
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
