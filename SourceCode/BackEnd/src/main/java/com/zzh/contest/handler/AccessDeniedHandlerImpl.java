package com.zzh.contest.handler;

import com.zzh.contest.utils.WebUtils;
import com.zzh.contest.utils.result.Result;
import com.zzh.contest.utils.result.ResultCode;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        // 鉴定权限失败自定义返回的信息
        WebUtils.responseJson(response, Result.failure(ResultCode.AUTHENTICATION_FAIL));
    }
}
