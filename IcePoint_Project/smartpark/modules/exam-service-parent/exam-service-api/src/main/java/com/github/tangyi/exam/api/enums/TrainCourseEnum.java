package com.github.tangyi.exam.api.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author xh
 * @Description 培训报名功能用到的 枚举
 * @Date 14:55 2020/10/18
 * @Param
 * @return
 **/

public class TrainCourseEnum {

    @Getter
    @AllArgsConstructor
    public static enum IS_SIGN_IN {

        sign_in(1,"已签到"),
        no_sign_in(0,"未签到");

        private Integer value;
        private String  desc;

    }

    @Getter
    @AllArgsConstructor
    public static enum CourseStatus {

        ready(1,"启用"),
        not_ready(0,"停用");

        private Integer value;
        private String  desc;

        public static CourseStatus getByValue(Integer value){
            CourseStatus[] values = CourseStatus.values();
            for (int i = 0; i <values.length ; i++) {
                if(values[i].value.equals(value)) {
                    return values[i];
                }
            }
            return not_ready;//默认 返回 停用的
        }

    }
}
