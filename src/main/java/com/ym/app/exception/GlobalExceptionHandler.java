package com.ym.app.exception;

import com.ym.app.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //全局异常处理器，处理参数校验失败异常
    /*@ExceptionHandler(value = Exception.class)
    public Result handleException(Exception e) {
        e.printStackTrace();
        return Result.failure(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }*/

    //全局异常处理器，优雅地处理校验失败的异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<?> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        StringBuilder errorMessage = new StringBuilder("参数校验失败：");

        // 提取校验失败的字段和错误信息
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            errorMessage.append(fieldError.getField()) // 获取字段名
                    .append(": ")
                    .append(fieldError.getDefaultMessage()) // 获取错误提示
                    .append("; ");
        }

        return Result.failure(errorMessage.toString());
    }
}
