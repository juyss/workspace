package com.springAPI.staticproxy;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Landlord
 * @Desc: 房东
 * @package com.springAPI.staticproxy
 * @project KuangStudy-Spring
 * @date 2020/9/7 19:40
 */
public class Landlord implements Rent{

    /**
     * 房东出租房子
     */
    @Override
    public void RentOut() {
        System.out.println("-->真实房东出租房子");
    }

}
