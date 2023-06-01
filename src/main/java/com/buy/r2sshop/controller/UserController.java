package com.buy.r2sshop.controller;

import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.payload.LoginRequest;
import com.buy.r2sshop.payload.LoginResponse;
import com.buy.r2sshop.payload.RegisterRequest;
import com.buy.r2sshop.service.IUserService;
import com.buy.r2sshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(new LoginResponse(token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> adminEndpoint(@AuthenticationPrincipal UserDetails userDetails) {
        // Kiểm tra thông tin về quyền từ token
        String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        if (role.equals("ADMIN")) {
            return ResponseEntity.ok("Admin Endpoint");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<?> userEndpoint(@AuthenticationPrincipal UserDetails userDetails) {
        // Kiểm tra thông tin về quyền từ token
        String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
        if (role.equals("USER")) {
            return ResponseEntity.ok("User Endpoint");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }
    }

    //Get, Update tất cả thông tin của User đang login.
    @GetMapping("/user-info")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user-info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(Authentication authentication, @RequestBody User updatedUserData) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        User updatedUser = userService.updateUser(user.getId(), updatedUserData);
        return ResponseEntity.ok(updatedUser);
    }


}


