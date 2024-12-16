package com.ym.app.dto;

import lombok.Data;

@Data
public class UpdateUserDto {
    private String nickname;
    private String email;
    private String phone;
    private String avatarUrl;
    private String notificationType;
}