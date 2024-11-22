package com.example.overwatchbe.domain.user.controller;

import com.example.overwatchbe.domain.user.dto.LogoutRequest;
import com.example.overwatchbe.domain.user.dto.UserLoginRequest;
import com.example.overwatchbe.domain.user.dto.UserResponse;
import com.example.overwatchbe.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody UserLoginRequest loginRequest) {
        UserResponse response = userService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest logoutRequest) {
        userService.logout(logoutRequest);
        return ResponseEntity.ok("Successfully logged out");
    }

}
