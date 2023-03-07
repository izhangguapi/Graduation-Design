package com.zzh.contest.filter;

import com.zzh.contest.utils.RedisCache;
import com.zzh.contest.utils.WebUtils;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {
    @Resource
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 判断请求地址是否为登录请求
        if (request.getRequestURI().equals("/api/login1")) {
            // 获取用户的ulid和输入的验证码
            String ulid = request.getParameter("ulid");
            // System.out.println("用户的ulid：" + ulid);
            String captcha = request.getParameter("captcha");
            // System.out.println("用户输入的验证码：" + captcha);
            // 判断用户是否输入了验证码
            if (!StringUtils.hasText(captcha) || !StringUtils.hasText(ulid)) {
                // 用户没有输入验证码
                WebUtils.responseJson(response, Result.failure(ResultCode.NO_CAPTCHA));
            } else {
                // 获取redis中的验证码
                String serverCaptcha = redisCache.getCacheObject(ulid);
                // System.out.println("服务器存储的验证码：" + serverCaptcha);
                // 判断用户输入的验证码是否正确
                if (captcha.equals(serverCaptcha)) {
                    filterChain.doFilter(request, response);
                } else {
                    // 验证码不正确或已失效
                    WebUtils.responseJson(response, Result.failure(ResultCode.CAPTCHA_ERROR_OR_EXPIRE));
                }
            }
        } else {
            // 放行到下一个过滤器
            filterChain.doFilter(request, response);
        }
    }
}
