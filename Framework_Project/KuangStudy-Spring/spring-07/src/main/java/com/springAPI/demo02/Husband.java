package com.springAPI.demo02;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Husband
 * @Desc: 被代理类
 * @package com.springAPI.demo02
 * @project KuangStudy-Spring
 * @date 2020/9/4 15:50
 */
public class Husband implements WeddingCompany{

    @Override
    public void getMarried(){
        System.out.println("真实的Husband,getMarried");
    }
}
