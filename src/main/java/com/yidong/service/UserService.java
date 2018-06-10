package com.yidong.service;

public interface UserService {
    String login(String account, String password);
    String refresh(String oldToken);
}
