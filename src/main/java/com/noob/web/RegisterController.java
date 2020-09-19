package com.noob.web;

import com.noob.domain.User;
import com.noob.kit.ConstantKit;
import com.noob.kit.TokenGenerator;
import com.noob.service.RedisService;
import com.noob.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Api("RegisterController")
@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public String register(User userParam,
                           HttpSession session) {
        User user = userService.createUser(userParam);
        if (user == null) {
            // 用户创建失败
            return "error/error";
        }
        // 用户创建成功
        String token = tokenGenerator.generate(new String[]{user.getUsername(), user.getPassword()});
        redisService.set(user.getUsername(), token);
        redisService.expire(user.getUsername(), ConstantKit.TOKEN_RESET_TIME);

        redisService.set(token, user.getUsername());
        redisService.expire(token, ConstantKit.TOKEN_RESET_TIME);

        redisService.set(token + user.getUsername(), String.valueOf(System.currentTimeMillis()));

        session.setAttribute("token", token);
        session.setAttribute("user", user);

        return "redirect:/people";
    }
}
