package com.shorlink.project.common.convention.result;

import com.shorlink.project.common.convention.errorcode.BaseErrorCode;
import com.shorlink.project.common.convention.exception.AbstractException;


import java.util.Optional;
/**
 * @Description 构造全局返回值
 * @auther j2-yizhiyang
 * @date 2023/12/17 16:33
 */
public final class Results {
    /**
     * 构造成功响应
     */
    public static Result<Void> success(){
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE);
    }
    /**
     * 构造带返回数据的成功响应
     */
    public static <T> Result<T> success(T data){
        return new Result<T>()
                .setCode(Result.SUCCESS_CODE)
                .setData(data);
    }
    /**
     * 构建服务端失败响应
     */
    public static Result<Void> failure(){
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE)
                .setMessage(BaseErrorCode.CLIENT_ERROR.code());
    }
    /**
     * 通过 {@link AbstractException} 构建失败响应
     */
    public static Result<Void> failure(AbstractException abstractException){
        String erroeCode = Optional.ofNullable(abstractException.getErrorCode())
                .orElse(BaseErrorCode.CLIENT_ERROR.code());
        String errrorMessage = Optional.ofNullable(abstractException.getErrorMessage())
                .orElse(BaseErrorCode.CLIENT_ERROR.code());
        return new Result<Void>()
                .setCode(erroeCode)
                .setMessage(errrorMessage);
    }
    /**
     * 通过 errorCode、errorMessage 构建失败响应
     */
    public static Result<Void> failure(String errorCode, String errorMessage) {
        return new Result<Void>()
                .setCode(errorCode)
                .setMessage(errorMessage);
    }
}
