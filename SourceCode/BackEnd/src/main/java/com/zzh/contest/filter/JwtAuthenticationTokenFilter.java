package com.zzh.contest.filter;

import com.alibaba.fastjson2.JSON;
import com.zzh.contest.entity.dto.Login;
import com.zzh.contest.utils.JwtUtils;
import com.zzh.contest.utils.RedisCache;
import com.zzh.contest.utils.WebUtils;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        // 获取token
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            WebUtils.responseJson(response, Result.failure(ResultCode.NO_TOKEN));
            return;
        }
        // 解析token
        Integer uid;
        try {
            Claims claims = JwtUtils.checkToken(token);
            // 获取用户id
            assert claims != null;
            uid = claims.get("uid", Integer.class);
        } catch (Exception e) {
            WebUtils.responseJson(response, Result.failure(ResultCode.TOKEN_ILLEGAL));
            return;
        }
        // 获取用户信息
        Object obj = redisCache.getCacheObject("login:" + uid);
        Login login = JSON.parseObject(JSON.toJSONString(obj), Login.class);
        if (Objects.isNull(login)) {
            WebUtils.responseJson(response, Result.failure(ResultCode.LOGIN_EXPIRE));
            return;
        }
        // 封装Authentication
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(login, null, login.getAuthorities());
        // 存入SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        // 放行，让后面的过滤器执行
        filterChain.doFilter(request, response);
    }
}