package com.smartbooker.controller;

import com.smartbooker.dto.AuthRequest;
import com.smartbooker.dto.AuthResponse;
import com.smartbooker.model.Role;
import com.smartbooker.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public AuthResponse signup(@RequestBody AuthRequest request) {
        return authService.signup(request, Role.USER);
    }

    @PostMapping("/admin/signup")
    public AuthResponse adminSignup(@RequestBody AuthRequest request) {
        return authService.signup(request, Role.ADMIN);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
