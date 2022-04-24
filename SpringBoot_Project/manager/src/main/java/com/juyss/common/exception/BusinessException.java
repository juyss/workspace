package com.juyss.common.exception;

import com.juyss.common.exception.code.BaseResponseCode;
import com.juyss.common.exception.code.ResponseCodeInterface;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: BusinessException
 * @Desc: 业务异常
 * @package com.juyss.common.exception
 * @project manager
 * @date 2021/1/12 17:17
 */
public class BusinessException extends RuntimeException {
    /**
     * 异常编号
     */
    private final int messageCode;

    /**
     * 对messageCode 异常信息进行补充说明
     */
    private final String detailMessage;

    public BusinessException(int messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
        this.detailMessage = message;
    }

    public BusinessException(String message) {
        super(message);
        this.messageCode = BaseResponseCode.OPERATION_ERROR.getCode();
        this.detailMessage = message;
    }

    /**
     * 构造函数
     *
     * @param code 异常码
     */
    public BusinessException(ResponseCodeInterface code) {
        this(code.getCode(), code.getMsg());
    }

    public int getMessageCode() {
        return messageCode;
    }

    public String getDetailMessage() {
        return detailMessage;
    }
}

