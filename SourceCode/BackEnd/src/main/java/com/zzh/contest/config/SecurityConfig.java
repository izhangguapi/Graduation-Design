package com.zzh.contest.config;

import com.zzh.contest.filter.CaptchaFilter;
import com.zzh.contest.filter.JwtAuthenticationTokenFilter;
import com.zzh.contest.handler.AccessDeniedHandlerImpl;
import com.zzh.contest.handler.AuthenticationEntryPointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
// @EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Resource
    JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Resource
    private CaptchaFilter captchaFilter;
    @Resource
    AuthenticationEntryPointImpl authenticationEntryPoint;
    @Resource
    AccessDeniedHandlerImpl accessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置哪些页面需要登录，哪些不需要的拦截规则
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 关闭csrf防护
                .csrf().disable()
                // 不通过session获取securityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 设置退出登录的路径,避免与官方默认路径相同
                .logout().logoutUrl("/disable/logout")
                .and()
                .authorizeRequests()
                // 配置登录注册相关的接口允许匿名访问;只有未登录的人才能访问
                .antMatchers("/login", "/register", "/captcha", "/ip")
                .anonymous()
                // 静态资源放行;不管登录与否都能访问
                .antMatchers("/logout", "/js/**", "/css/**", "/jQuery/**", "/images/**", "/icon/**", "/file/**")
                .permitAll()
                // 除去上面的所有请求都需要鉴权认证
                .anyRequest().authenticated();
        // 自定义的验证码过滤器放在user过滤器之前
        // http.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
        // 把自定义的过滤器(jwt登录授权过滤器)放在user过滤器之前
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 自定义异常处理（无权限或登录失败）
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
        return http.build();
    }

    // 获取AuthenticationManager（认证管理器），登录时认证使用
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}