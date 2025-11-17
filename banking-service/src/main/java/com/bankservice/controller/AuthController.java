package com.bankservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bankservice.util.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {

        // DEMO PURPOSE ONLY:
        // admin/password123 -> ADMIN
        // user/password123 -> USER

        String role;

        if (username.equals("admin") && password.equals("password123"))
            role = "ADMIN";
        else if (username.equals("user") && password.equals("password123"))
            role = "USER";
        else
            throw new RuntimeException("Invalid Credentials");

        return jwtUtil.generateToken(username, role);
    }
}
