package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.Cart;
import com.buy.r2sshop.entity.Role;
import com.buy.r2sshop.entity.User;
import com.buy.r2sshop.repository.CartRepository;
import com.buy.r2sshop.repository.RoleRepository;
import com.buy.r2sshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


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

    //
}
