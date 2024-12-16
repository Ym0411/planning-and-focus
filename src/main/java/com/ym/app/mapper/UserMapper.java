package com.ym.app.mapper;

import com.ym.app.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO users (username, password, nickname, email, phone, avatar_url, notification_type, status, created_at, updated_at) " +
            "VALUES (#{username}, #{password}, #{nickname}, #{email}, #{phone}, #{avatarUrl}, #{notificationType}, 1, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insertUser(User user);

    @Select("SELECT * FROM users WHERE username = #{username} AND status = 1")
    Optional<User> findByUsername(String username);

    @Select("SELECT * FROM users WHERE id = #{id} AND status = 1")
    Optional<User> findById(Long id);

    @Update("UPDATE users SET password = #{password}, updated_at = now() WHERE id = #{id} /*AND status = 1*/")
    void updatePassword(@Param("id") Long id, @Param("password") String password);

    @Update("UPDATE users SET nickname = #{nickname}, email = #{email}, phone = #{phone}, avatar_url = #{avatarUrl}, notification_type = #{notificationType}, updated_at = now() WHERE id = #{id} /*AND status = 1*/")
    void updateUser(User user);

    @Update("UPDATE users SET status = 0, updated_at = now() WHERE id = #{id}")
    void deactivateUser(@Param("id") Long id);
}