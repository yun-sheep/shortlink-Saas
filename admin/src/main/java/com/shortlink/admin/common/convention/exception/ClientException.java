package com.shortlink.admin.common.convention.exception;

import com.shortlink.admin.common.convention.errorcode.BaseErrorCode;
import com.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @Description 客户端异常
 * @auther j2-yizhiyang
 * @date 2023/12/17 16:15
 */
public class ClientException extends AbstractException{
    public ClientException(Throwable throwable, IErrorCode errorCode, String message) {
        super(throwable, errorCode, message);
    }
    public ClientException(IErrorCode errorCode) {
        this(null, errorCode, null);
    }

    public ClientException(String message) {
        this(null, BaseErrorCode.CLIENT_ERROR, message);
    }

    public ClientException(String message, IErrorCode errorCode) {
        this(null, errorCode, message);
    }
    public  String toString() {
        return "ClientException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }

}
