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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Api(tags = "LoginController", description = "登录管理")
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private RedisService redisService;

    @ApiOperation("登录，返回用户对象")
    @PostMapping("/login")
    public String login(User userParam,
                        HttpSession session) {
        List<User> userList = userService.searchUserByUsernameAndPassword(userParam);
        User user = null;

        // 不下放到 service 层了，定义这个方法不保证返回唯一
        if (userList != null && userList.size() == 1)
            user = userList.get(0);

        if (user == null) {
            // 登录失败
            return ("error/error");
        }
        // 生成 token
        String token = tokenGenerator.generate(new String[]{user.getUsername(), user.getPassword()});
        redisService.set(user.getUsername(), token);
        // expire：指定新的生存时间，并取代旧的生存时间
        // 应该是设置过期时间
        redisService.expire(user.getUsername(), ConstantKit.TOKEN_EXPIRE_TIME);
        redisService.set(token, user.getUsername());
        redisService.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
        long currentTime = System.currentTimeMillis();
        redisService.set(token + user.getUsername(), String.valueOf(currentTime));

        session.setAttribute("token", token);
        session.setAttribute("user", user);

        return "redirect:/people";
    }


}
