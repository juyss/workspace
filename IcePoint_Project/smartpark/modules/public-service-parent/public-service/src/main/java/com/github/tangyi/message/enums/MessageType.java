package com.github.tangyi.message.enums;

import lombok.AllArgsConstructor;


import lombok.Getter;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: MessageType
 * @Desc:  留言类型枚举
 * @package com.github.tangyi.message
 * @project park
 * @date 2021/3/27 10:53
 */
@Getter
@AllArgsConstructor
public enum MessageType {
    ADVISORY("咨询",1),
    SUGGEST("建议",2),
    COMPLAINT("投诉",3),
    SHARE("分享",4);

    /**
     * 留言类型
     */
    private String name;

    /**
     * 留言类型对应值
     */
    private Integer value;
}
