package com.github.tangyi.core.common.exception;

import com.github.tangyi.core.common.web.BaseResultCode;
import com.github.tangyi.core.common.web.ResultCode;

/**
 * 业务性质的异常处理类
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public class BusinessException extends RuntimeException {

    /**
     * 结果码
     */
    protected Integer code = BaseResultCode.common_business_error.getCode();

    /**
     * 消息
     */
    protected String message;

    /**
     * 消息参数
     */
    protected Object[] args;

    /**
     * 数据
     */
    protected Object data;

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(Integer code, String message, Object[] args) {
        super(message);
        this.code = code;
        this.message = message;
        this.args = args;
    }

    public BusinessException(ResultCode resultCode, Object... args) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.args = args;
    }

    public BusinessException(Throwable cause, Integer code, String message) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    /**
     * 抛异常
     *
     * @param resultCode
     * @param args
     */
    public static void throwEx(ResultCode resultCode, Object... args) {
        throw new BusinessException(resultCode, args);
    }

    /**
     * 抛异常
     *
     * @param resultCode
     * @param data
     * @param args
     */
    public static void throwExHasData(ResultCode resultCode, Object data, Object... args) {
        BusinessException exception = new BusinessException(resultCode, args);
        exception.setData(data);
        throw exception;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

//    @Override
//    public String getLocalizedMessage() {
//        return ApplicationContext.getMessage(message, args);
//    }

}
