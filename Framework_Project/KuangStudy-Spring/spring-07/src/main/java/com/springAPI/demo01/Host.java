package com.springAPI.demo01;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Host
 * @Desc: 真实想要出租的人(被代理类)
 * @package com.springAPI.demo01
 * @project KuangStudy-Spring
 * @date 2020/9/4 13:37
 */
public class Host implements Rent{

    /**
     * 房东想要出租房子
     */
    @Override
    public void rent() {
        System.out.println("房东想要出租房子");
    }
}
