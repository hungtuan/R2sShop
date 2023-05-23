package com.buy.r2sshop.controller;

import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.payload.AuthenticationRequest;
import com.buy.r2sshop.payload.AuthenticationResponse;
import com.buy.r2sshop.payload.RegisterRequest;
import com.buy.r2sshop.service.IUserService;
import com.buy.r2sshop.service.UserService;
import com.buy.r2sshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UserController {
    private final AuthenticationManager authenticationManager;

    private final IUserService userService;

    private final JwtUtil jwtUtil;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/register/user")
    public ResponseEntity<String> registerUserAsUser(@RequestBody RegisterRequest registerRequest) {
        userService.registerUserAsUser(registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok("User registered as User successfully.");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register/admin")
    public ResponseEntity<String> registerUserAsAdmin(@RequestBody RegisterRequest registerRequest) {
        userService.registerUserAsAdmin(registerRequest.getUsername(), registerRequest.getPassword());
        return ResponseEntity.ok("User registered as Admin successfully.");
    }

    //Đăng nhập



}


