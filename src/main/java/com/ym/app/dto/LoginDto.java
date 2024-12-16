package com.ym.app.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDto {
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,12}$", message = "用户名必须由4到12位字母、数字或下划线组成")
    private String username;
    @Pattern(regexp = "^[a-zA-Z0-9_]{6,16}$", message = "密码必须由6到16位字母、数字或下划线组成")
    private String password;
}