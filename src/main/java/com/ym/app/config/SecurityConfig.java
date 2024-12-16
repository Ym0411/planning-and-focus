/*
package com.ym.app.config;

import com.ym.app.service.impl.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
*/

/*
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {


    private final UserDetailsServiceImpl userDetailsServiceImpl;
    //在开发环境中：配置 Spring Security 的安全过滤规则，允许注册和登录接口匿名访问。
    //生产环境中：建议开启 CSRF，并为需要保护的接口设置 Token 验证。

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // 禁用 CSRF
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/users/register", "/api/users/login").permitAll() // 开放注册和登录接口
                        .requestMatchers("/api/users/**").permitAll() // 开发测试时，用户相关接口需要认证
                        .anyRequest().authenticated() // 其他接口需要身份验证
                )
                .formLogin(form -> form.disable()) // 禁用表单登录
                .httpBasic(httpBasic -> httpBasic.disable()); // 禁用 HTTP Basic

        return http.build();
    }

    */
/*//*
/ 处理用户认证逻辑
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }*//*


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 使用 BCrypt 进行密码加密
    }

    */
/*//*
/ 从Spring Security的默认配置中获取AuthenticationManager实例并注入Spring容器，使其可被控制器中的登录认证使用
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }*//*

}*/
