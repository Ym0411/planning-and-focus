package com.ym.app.dto;

import lombok.Data;

@Data
public class UserInfoDto {
    private Long id;               // 用户 ID
    private String username;       // 用户名
    private String nickname;       // 昵称
    private String email;          // 邮箱
    private String phone;          // 手机号
    private String avatarUrl;      // 头像 URL
    private String notificationType; // 通知类型
    private String createdAt;      //用户创建时间
    private String updatedAt;       //用户信息修改时间

}

