package com.noob.dao;

import com.noob.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserDAO {

    @Insert(" insert into user (username, password, create_date, modify_date) " +
            "values (#{username}, #{password}, #{createDate}, #{modifyDate}) ")
    void insertUser(User user);

    @Select(" select * from user where username = #{username} and password = #{password} ")
    List<User> selectUser(User user);
}
