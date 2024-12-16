package com.ym.app.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Long id;                   // 用户唯一标识
    private String username;           // 用户名
    private String password;           // 密码
    private String nickname;           // 用户昵称
    private String email;              // 用户邮箱
    private String phone;              // 用户电话
    private String avatarUrl;          // 用户头像
    private String notificationType;   // 提醒方式
    private Integer status;            // 用户状态 (1: 激活, 0: 注销)
    private LocalDateTime createdAt;   // 创建时间
    private LocalDateTime updatedAt;   // 更新时间
}