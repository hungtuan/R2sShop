package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService  {

    void registerUserAsUser(String username, String password);

    void registerUserAsAdmin(String username, String password);

}
