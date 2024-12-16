package com.ym.app.service.impl;

import com.ym.app.dto.*;
import com.ym.app.mapper.UserMapper;
import com.ym.app.pojo.User;
import com.ym.app.service.UserService;
import com.ym.app.util.JwtUtil;
import com.ym.app.util.Md5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserMapper userMapper;


    @Override
    public void registerUser(RegisterDto registerDto) {
        if (userMapper.findByUsername(registerDto.getUsername()).isPresent()) {
            throw new RuntimeException("用户名已存在");
        }

        // 使用 Md5Util 加密密码
        String encryptedPassword = Md5Util.getMD5String(registerDto.getPassword());

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setPassword(encryptedPassword); // 存储加密后的密码
        user.setNickname(registerDto.getNickname());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setAvatarUrl(registerDto.getAvatarUrl());
        user.setNotificationType(registerDto.getNotificationType());

        userMapper.insertUser(user);
    }

    @Override
    public String loginUser(LoginDto loginDto) {
        User user = userMapper.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        if (!Md5Util.checkPassword(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 构造业务数据（如用户 ID 和角色等）放入 JWT
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        /*claims.put("email", user.getEmail());*/
        String token = JwtUtil.genToken(claims);

        return token;
    }

    @Override
    public UserInfoDto getUserById(Long id) {
        User user = userMapper.findById(id)
                .orElseThrow(() -> new RuntimeException("用户不存在"));
        return convertToUserInfoDto(user); // 转换为 UserInfoDto
    }
    // 将 User 实体转换为 UserInfoDto
    private UserInfoDto convertToUserInfoDto(User user) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setId(user.getId());
        userInfoDto.setUsername(user.getUsername());
        userInfoDto.setEmail(user.getEmail());
        userInfoDto.setPhone(user.getPhone());
        userInfoDto.setAvatarUrl(user.getAvatarUrl());
        userInfoDto.setNotificationType(user.getNotificationType());
        userInfoDto.setNickname(user.getNickname());
        return userInfoDto;
    }

    @Override
    public void changePassword(Long userId, ChangePasswordDto changePasswordDto) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        // 验证旧密码是否正确
        if (!Md5Util.checkPassword(changePasswordDto.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("旧密码错误");
        }

        // 检查新密码是否与旧密码不同
        String newEncryptedPassword = Md5Util.getMD5String(changePasswordDto.getNewPassword());
        if (newEncryptedPassword.equals(user.getPassword())) {
            throw new RuntimeException("新密码不能与旧密码相同");
        }

        // 更新新密码
        userMapper.updatePassword(userId, newEncryptedPassword);
    }

    @Override
    public void updateProfile(Long userId, UpdateUserDto updateUserDto) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        user.setNickname(updateUserDto.getNickname());
        user.setEmail(updateUserDto.getEmail());
        user.setPhone(updateUserDto.getPhone());
        user.setAvatarUrl(updateUserDto.getAvatarUrl());
        user.setNotificationType(updateUserDto.getNotificationType());

        userMapper.updateUser(user);
    }

    @Override
    public void logout() {
        // 模拟退出登录，生产环境应实现清理 Token 或 Session 的逻辑
    }

    @Override
    public void deactivateUser(Long userId) {
        User user = userMapper.findById(userId)
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        userMapper.deactivateUser(userId);
    }
}
