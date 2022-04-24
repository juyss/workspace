package com.github.tangyi.exam.api.constants;

/**
 * @Author xh
 * @Description 学习api 用到常量
 * @Date 8:14 2020/10/27
 * @Param
 * @return
 **/
public class StudyConstant {
    /**
     * redis 存学习记录的key
     */
    public final static String redis_study_key = "study:%s";

   /**********************************下面是一个工具方法********************************************/

    public static String getRedisStudyKey(Long userId){
        return String.format(redis_study_key,userId);
    }
}
