package com.zzh.contest.utils;

import com.alibaba.fastjson.JSON;
import com.zzh.contest.utils.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebUtils {
    /**
     * 将json数据响应给请求者
     *
     * @param response 渲染对象
     * @param result   统一返回数据
     */
    public static void responseJson(HttpServletResponse response, Result result) {
        String json = JSON.toJSONString(result);
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            response.getWriter().print(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}