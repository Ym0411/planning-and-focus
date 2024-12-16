package com.ym.app.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private Integer code;    // 业务状态码 200-成功 500-失败
    private String message;  // 提示信息
    private T data;          // 响应数据

    // 静态方法快速构造通用响应
    public static <E> Result<E> success(E data) {
        return new Result<>(200, "操作成功", data);
    }

    public static Result success() {
        return new Result<>(200, "操作成功", null);
    }

    public static Result success(String message) {
        return new Result<>(200, message, null);
    }
    public static <E> Result<E> success(String message, E data) {
        return new Result<>(200, message, data);
    }


    public static Result failure(String message) {
        return new Result<>(500, message, null);
    }

    /*public static Result failure(Integer code, String message) {
        return new Result<>(code, message, null);
    }*/
}
