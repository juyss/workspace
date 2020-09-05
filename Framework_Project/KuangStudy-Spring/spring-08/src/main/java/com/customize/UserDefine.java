package com.customize;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserDefine
 * @Desc: 用户自定义切面类
 * @package com.customize.customize
 * @project KuangStudy-Spring
 * @date 2020/9/5 18:22
 */
public class UserDefine {

    public void BeforeLog(){
        System.out.println("自定义类前置日志功能");
    }

    public void AfterLog(){
        System.out.println("自定义类后置日志功能");
    }
}
