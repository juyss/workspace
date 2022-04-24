package com.github.tangyi.core.common.web;

import com.github.tangyi.core.common.exception.BusinessException;

/**
 * 结果Code
 *
 * @author hedongzhou
 * @since 2018/07/02
 */
public interface ResultCode {

    /**
     * 获取Code
     *
     * @return
     */
    Integer getCode();

    /**
     * 获取Message
     *
     * @return
     */
    String getMessage();

    /**
     * 异常
     *
     * @param args
     */
    default void throwEx(Object... args) {
        BusinessException.throwEx(this, args);
    }

    /**
     * 异常
     *
     * @param data
     * @param args
     */
    default void throwExHasData(Object data, Object... args) {
        BusinessException.throwExHasData(this, data, args);
    }

}
