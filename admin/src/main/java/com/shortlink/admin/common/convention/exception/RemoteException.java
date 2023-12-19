package com.shortlink.admin.common.convention.exception;

import com.shortlink.admin.common.convention.errorcode.IErrorCode;

/**
 * @Description 远程服务调用异常
 * @auther j2-yizhiyang
 * @date 2023/12/17 16:18
 */
public class RemoteException extends  AbstractException{
    public RemoteException(Throwable throwable, IErrorCode errorCode, String message) {
        super(throwable, errorCode, message);
    }
    public RemoteException(String message, IErrorCode errorCode) {
        this(message, null, errorCode);
    }

    public RemoteException(String message, Throwable throwable, IErrorCode errorCode) {
        super(throwable, errorCode, message);
    }
    @Override
    public String toString() {
        return "RemoteException{" +
                "code='" + errorCode + "'," +
                "message='" + errorMessage + "'" +
                '}';
    }
}
