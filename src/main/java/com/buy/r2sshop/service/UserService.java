package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Cart;
import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.payload.AuthenticationRequest;
import com.buy.r2sshop.payload.AuthenticationResponse;
import com.buy.r2sshop.repository.UserRepository;
import com.buy.r2sshop.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;


import java.util.stream.Collectors;

@Service
public class UserService {
    public void registerUser(User user) {

    }

    public void Login() {

    }
}
