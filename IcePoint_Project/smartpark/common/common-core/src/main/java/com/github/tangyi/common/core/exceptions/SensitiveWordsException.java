package com.github.tangyi.common.core.exceptions;

/**
 * 敏感词异常
 *
 * @author gaokx
 * @date 2019/3/29 12:07
 */
public class SensitiveWordsException extends CommonException {

    private static final long serialVersionUID = -7285211528095468156L;

    public SensitiveWordsException() {
    }

    public SensitiveWordsException(String msg) {
        super(msg);
    }
}
