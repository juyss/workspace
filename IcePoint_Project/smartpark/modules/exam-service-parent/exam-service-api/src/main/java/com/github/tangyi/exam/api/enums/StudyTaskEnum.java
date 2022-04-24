package com.github.tangyi.exam.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 学习任务中的枚举
 */

public class StudyTaskEnum {

    @Getter
    @AllArgsConstructor
    public static enum StudyStatus {

        wait("wait", "未开始"),
        doing("doing", "进行中"),
        finish("finish", "已完成"),
        ;

        private String value;
        private String desc;

    }

    @Getter
    @AllArgsConstructor
    public static enum MyTaskListQueryType {

        all("所有"),
        current("当前");

        private String desc;

    }
}
