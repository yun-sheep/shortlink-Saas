package com.shorlink.project.common.convention.exception;


import com.shorlink.project.common.convention.errorcode.IErrorCode;

/**
 * @Description 服务端异常
 * @auther j2-yizhiyang
 * @date 2023/12/17 16:19
 */
public class ServiceException extends AbstractException{
    protected ServiceException(Throwable throwable, IErrorCode errorCode, String message) {
        super(throwable, errorCode, message);
    }
    public ServiceException(String message) {
        //this(message, null, BaseErrorCode.SERVICE_ERROR);
        this(message, null);

    }

    public ServiceException(IErrorCode errorCode) {
        this(null, errorCode);
    }

    public ServiceException(String message, IErrorCode errorCode) {
        this(null, errorCode, message);
    }
    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
