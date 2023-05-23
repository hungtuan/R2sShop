package com.buy.r2sshop.service;

public interface IUserService  {

    void registerUserAsUser(String username, String password);

    void registerUserAsAdmin(String username, String password);

    String login(String username, String password) throws Exception;

}
