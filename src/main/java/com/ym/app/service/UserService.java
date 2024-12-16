package com.ym.app.service;

import com.ym.app.dto.*;


public interface UserService {
    void registerUser(RegisterDto registerDto);

    String loginUser(LoginDto loginDto);

    UserInfoDto getUserById(Long id);

    /*void updateUser(User user);*/

    void changePassword(Long userId, ChangePasswordDto changePasswordDto);

    void updateProfile(Long userId, UpdateUserDto updateUserDto);

    void logout();

    void deactivateUser(Long userId);
}