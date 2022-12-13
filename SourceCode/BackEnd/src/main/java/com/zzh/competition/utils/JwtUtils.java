package com.zzh.competition.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * JWT工具类
 */
public class JwtUtils {
    // 过期时间7天
    static final long TIME = 1000 * 60 * 60 * 24 * 7;
    // 秘钥
    static final String SIGNATURE = "zgp99.J@";

    public static String createToken(String name, int uid, int gid, Boolean admin) {
        Map<String, Object> claims = new HashMap<>(3);
        claims.put("name", name);
        claims.put("uid", uid);
        claims.put("gid", gid);
        claims.put("isAdmin", admin);
        // 创建jwt对象
        return Jwts.builder()
                // 自定义body数据
                .setClaims(claims)
                // 设置签发算法
                .signWith(SignatureAlgorithm.HS256, JwtUtils.SIGNATURE)
                // 设置有效时间
                .setExpiration(new Date(System.currentTimeMillis() + JwtUtils.TIME))
                // 生成token字符串
                .compact();
    }

    /**
     * 验证解析token
     * JWT Token 由 头部 荷载部 和 签名部 三部分组成。签名部分是由加密算法生成，无法反向解密。
     * 而 头部 和 荷载部分是由 Base64 编码算法生成，是可以反向反编码回原样的。
     * 这也是为什么不要在 JWT Token 中放敏感数据的原因。
     *
     * @param token 加密后的token
     * @return claims 返回荷载部分的键值对
     */
    public static Claims checkToken(String token) {
        try {
            // 创建解析对象
            return Jwts.parser()
                    // 设置安全密钥（生成签名所需的密钥和算法）
                    .setSigningKey(JwtUtils.SIGNATURE)
                    // 解析token
                    .parseClaimsJws(token)
                    // 获取 payload 部分内容
                    .getBody();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 根据token字符串得到用户名称
     *
     * @param token token
     * @param key   键
     * @return token下body对应的数据
     */
    public static String getBody(String token, String key) {
        return String.valueOf(Objects.requireNonNull(JwtUtils.checkToken(token)).get(key));
    }
}