package com.juyss.common.exception.code;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: ResponseCodeInterface
 * @Desc: 异常响应接口
 * @package com.juyss.common.exception.code
 * @project manager
 * @date 2021/1/12 17:17
 */
public interface ResponseCodeInterface {
    /**
     * 获取code
     *
     * @return code
     */
    int getCode();

    /**
     * 获取信息
     *
     * @return msg
     */
    String getMsg();
}
