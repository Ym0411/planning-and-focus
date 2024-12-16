package com.ym.app.controller;

import com.ym.app.dto.*;
import com.ym.app.pojo.Result;
import com.ym.app.pojo.User;
import com.ym.app.service.UserService;
//import com.ym.app.util.JwtUtil;
import com.ym.app.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;*/
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //private final AuthenticationManager authenticationManager;

    //private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<?> register(@Valid @RequestBody RegisterDto registerDto) {
        userService.registerUser(registerDto);
        return Result.success("用户注册成功");
    }

    @PostMapping("/login")
    public Result<String> login(@Valid @RequestBody LoginDto loginDto) {
        String token = userService.loginUser(loginDto);
        return Result.success("用户登录成功", token);
    }

    /*@PostMapping("/login")
    public Result<String> login(@Valid @RequestBody LoginDto loginDto) {
        *//*String token = userService.loginUser(loginDto);
        return Result.success(token); // 返回登录生成的 Token*//*

        //登录认证
        Authentication authentication = authenticationManager.authenticate(
                //封装用户输入的用户名和密码
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );
        //验证成功，生成一个 JWT 并返回给用户
        String token = jwtUtil.generateToken(authentication.getName());
        return Result.success("登录成功", token);
    }*/

    // 通过id显示用户信息（也可以用username）
    @GetMapping("/userinfo")
    public Result<UserInfoDto> getUserInfoById(@RequestHeader("Authorization") String token) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        Long userId = ((Number) map.get("id")).longValue();
        UserInfoDto userInfoDto = userService.getUserById(userId);
        return Result.success(userInfoDto);
    }
    /*@GetMapping("/userinfo")
    public Result<?> getUserInfo(@RequestHeader("Authorization") String token) {
        try {
            *//*Long userId = (Long) JwtUtil.parseToken(token).get("id");*//*
            //Number 是 Integer 和 Long 的父类，longValue() 方法可以安全地将 Number 转换为 long 类型。
            Long userId = ((Number) JwtUtil.parseToken(token).get("id")).longValue();
            UserInfoDto userInfo = userService.getUserById(userId);
            return Result.success(userInfo);
        } catch (Exception e) {
            e.printStackTrace(); // 打印详细错误信息到控制台
            return Result.failure("获取用户信息失败: " + e.getMessage());
        }
    }*/

    // 修改密码
    @PutMapping("/{id}/password")
    public Result<?> changePassword(@PathVariable Long id, @RequestBody ChangePasswordDto changePasswordDto) {
        userService.changePassword(id, changePasswordDto);
        return Result.success("密码修改成功");
    }

    // 修改个人资料
    @PutMapping("/{id}/profile")
    public Result<?> updateProfile(@PathVariable Long id, @RequestBody UpdateUserDto updateUserDto) {

        userService.updateProfile(id, updateUserDto);
        return Result.success("个人资料修改成功");
    }

    // 退出登录
    @PostMapping("/logout")
    public Result<?> logout() {
        userService.logout();
        return Result.success("退出登录成功");
    }

    // 注销账号
    @DeleteMapping("/{id}/delete")
    public Result<?> deactivateUser(@PathVariable Long id) {
        userService.deactivateUser(id);
        return Result.success("账号注销成功");
    }

}
