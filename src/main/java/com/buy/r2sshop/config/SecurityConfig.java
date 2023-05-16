package com.buy.r2sshop.config;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class SecurityConfig {
    public static String encoder(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean check(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
}
