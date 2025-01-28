package com.alperen.flightplanner.controller;

import com.alperen.flightplanner.model.User;
import com.alperen.flightplanner.security.JwtTokenProvider;
import com.alperen.flightplanner.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User saveUser) {
        User user = userService.saveUser(saveUser);
        return ResponseEntity.ok(user);
    }
    @GetMapping("/getCurrentUser")
    public ResponseEntity<User> getCurrentUser(@RequestHeader("Authorization") String token) {
        String jwt = token.substring(7);
        String username = jwtTokenProvider.getUsernameFromToken(jwt);
        User user = userService.getUserByUsername(username);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
