package com.noob.service.impl;

import com.noob.dao.UserDAO;
import com.noob.domain.User;
import com.noob.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    @Transactional
    public User createUser(User userParam) {
        User user = new User();

        BeanUtils.copyProperties(userParam, user);
        user.setCreateDate(System.currentTimeMillis());
        user.setModifyDate(user.getCreateDate());
        userDAO.insertUser(user);

        return user;
    }

    @Override
    public List<User> searchUserByUsernameAndPassword(User userParam) {
        return userDAO.selectUser(userParam);
    }

}
