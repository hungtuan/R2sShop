package com.buy.r2sshop.service;

import com.buy.r2sshop.entity.User;

public interface IUserService  {

    void registerUserAsUser(String username, String password);

    void registerUserAsAdmin(String username, String password);

    String login(String username, String password);

    User getUserByUsername(String username);

    User updateUser(Integer userId, User updatedUser);

}
