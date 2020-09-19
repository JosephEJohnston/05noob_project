package com.noob.interceptor;

import com.noob.annotation.AuthToken;
import com.noob.common.api.CommonResult;
import com.noob.kit.ConstantKit;
import com.noob.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    // 存放鉴权信息的 Header 名称，默认是 Authorization；
    private String httpHeaderName = "Authorization";

    //鉴权失败后返回的HTTP错误码，默认为401
    private int unauthorizedErrorCode = HttpServletResponse.SC_UNAUTHORIZED;

    // 存放登录用户模型 Key 的 Request Key
    public static final String REQUEST_CURRENT_KEY = "REQUEST_CURRENT_KEY";

    @Autowired
    private RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod))
            return true;
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 如果打上了 AuthToken 注解则需要验证 token
        if (method.getAnnotation(AuthToken.class) != null ||
                handlerMethod.getBeanType().getAnnotation(AuthToken.class) != null) {
            // 获取 token，存在名为 Authorization 的请求头里
            String token = (String) request.getSession().getAttribute("token");
            log.info("token is {}", token);
            String username = null;

            // 利用 token 获取 username
            if (token != null && token.length() != 0) {
                username = redisService.get(token);
                log.info("username is {}", username);
            }
            
            if (username != null && !username.trim().equals("")) {
                // 显示 token 存活时间
                long tokenBirthTime = Long.parseLong(redisService.get(token + username));
                log.info("token Birth time is: {}", tokenBirthTime);
                long diff = System.currentTimeMillis() - tokenBirthTime;
                log.info("token is exist : {} ms", diff);

                if (diff > ConstantKit.TOKEN_RESET_TIME) {
                    // 当存活时间大于重置时间时，重置 username 和 token 键值对的存活时间
                    redisService.expire(username, ConstantKit.TOKEN_EXPIRE_TIME);
                    redisService.expire(token, ConstantKit.TOKEN_EXPIRE_TIME);
                    log.info("Reset expire time success!");

                    // 设置新的出生时间
                    long newBirthTime = System.currentTimeMillis();
                    redisService.set(token + username, String.valueOf(newBirthTime));
                }

                return true;
            } else {
                response.sendRedirect("/passport");
                return false;
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
