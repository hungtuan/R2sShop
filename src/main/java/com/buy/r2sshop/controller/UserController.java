package com.buy.r2sshop.controller;

import com.buy.r2sshop.dao.UserDao;
import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.payload.AuthenticationRequest;
import com.buy.r2sshop.payload.AuthenticationResponse;
import com.buy.r2sshop.payload.RegisterRequest;
import com.buy.r2sshop.service.UserService;
import com.buy.r2sshop.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    private final UserDao userDao;

    private final JwtUtil jwtUtil;

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody User user) {
//
//    }
//

    //Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody AuthenticationRequest request
    ) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        final UserDetails user = userDao.findUserByUsername(request.getUsername());
        if (user != null) {
            return ResponseEntity.ok(jwtUtil.generateToken(user));
        }
        return ResponseEntity.status(400).body("Some error has occurred");
    }


}


