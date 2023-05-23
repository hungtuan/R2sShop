package com.buy.r2sshop.service;

import com.buy.r2sshop.config.CustomUserDetailsService;
import com.buy.r2sshop.entity.Cart;
import com.buy.r2sshop.entity.Role;
import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.repository.CartRepository;
import com.buy.r2sshop.repository.RoleRepository;
import com.buy.r2sshop.repository.UserRepository;
import com.buy.r2sshop.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public void registerUserAsUser(String username, String password) {
        Role userRole = roleRepository.findByName("USER");

        // Thực hiện đăng ký người dùng với vai trò User
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(userRole);
        saveUser(user);
    }

    public void registerUserAsAdmin(String username, String password) {
        Role adminRole = roleRepository.findByName("ADMIN");

        // Thực hiện đăng ký người dùng với vai trò Admin
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(adminRole);
        saveUser(user);
    }


    private void saveUser(User user) {
        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Thực hiện lưu người dùng vào cơ sở dữ liệu
        userRepository.save(user);

        // Tạo một giỏ hàng mới cho người dùng vừa tạo
        Cart cart = new Cart();
        cart.setUser(user);
        cartRepository.save(cart);
    }

    //Thực hiện đăng nhập và trả về token
    public String login(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
            String role = userDetails.getAuthorities().isEmpty() ? "" : userDetails.getAuthorities().iterator().next().getAuthority();
            return jwtUtil.generateToken(userDetails, role);
        } catch (AuthenticationException e) {
            throw new Exception("Invalid username or password");
        }
    }
}
