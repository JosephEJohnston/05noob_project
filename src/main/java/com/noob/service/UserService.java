package com.noob.service;


import com.noob.domain.User;

import java.util.List;

public interface UserService {

    // 创建用户
    User createUser(User user);

    // 查找用户
    List<User> searchUserByUsernameAndPassword(User user);
}
