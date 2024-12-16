/*
package com.ym.app.service.impl;

import com.ym.app.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//实现 UserDetailsService 接口从数据库中加载用户，自定义用户加载逻辑
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.ym.app.pojo.User user = userMapper.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("用户名不存在"));

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword()) // 密码从数据库加载
                .roles("USER") // 默认角色
                .build();
    }
}
*/
