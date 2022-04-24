package com.github.tangyi.core.common.web;

/**
 * 返回结果常用Code
 *
 * @author hedongzhou
 * @date 2018/06/13
 */
public enum BaseResultCode implements ResultCode {
    /**
     * 成功
     */
    success(1, "success"),
    /**
     * 失败
     */
    failure(2, "failure"),
    /**
     * 参数非空
     */
    required(3, "Parameters nonempty"),
    /**
     * 通用的业务异常
     */
    common_business_error(90, "异常"),
    /**
     * 参数非空
     */
    no_login(99, "未登录");

    final int code;

    final String message;

    BaseResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
