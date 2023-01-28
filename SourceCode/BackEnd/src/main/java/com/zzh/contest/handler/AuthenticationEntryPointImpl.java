package com.zzh.contest.handler;

import com.zzh.contest.utils.WebUtils;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 登录失败自定义返回的信息
        WebUtils.responseJson(response, Result.failure(ResultCode.LOGIN_FAIL));
    }
}
