package com.shortlink.admin.common.convention.exception;

import com.shortlink.admin.common.convention.errorcode.IErrorCode;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @Description 抽象项目中三类异常体系，客户端异常、服务端异常以及远程服务调用异常(用以恶个
 * @auther j2-yizhiyang
 * @date 2023/12/17 16:06
 *
 */
@Getter
public  abstract class AbstractException extends RuntimeException {
    public final String errorCode;

    public final String errorMessage;

    protected AbstractException(Throwable throwable, IErrorCode errorCode, String message) {
        super(message, throwable);
        this.errorCode = errorCode.code();
        this.errorMessage = Optional.ofNullable(StringUtils.hasLength(message)?message:null).orElse(errorCode.message());
    }
}
