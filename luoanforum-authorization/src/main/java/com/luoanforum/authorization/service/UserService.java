package com.luoanforum.authorization.service;

import java.util.Map;

public interface UserService {
    Map<String, Object> getUserInfo(String username);
}
