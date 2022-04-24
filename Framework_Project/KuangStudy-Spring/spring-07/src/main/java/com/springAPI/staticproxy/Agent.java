package com.springAPI.staticproxy;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: Agent
 * @Desc: 中介 可以在不改变被代理类代码的情况下,添加其他功能
 * @package com.springAPI.staticproxy
 * @project KuangStudy-Spring
 * @date 2020/9/7 19:43
 */
public class Agent implements Rent{

    private Rent rent;

    public Agent(Rent rent) {
        this.rent = rent;
    }

    /**
     * 中介出租房子
     */
    @Override
    public void RentOut() {
        showHouse();
        System.out.println("中介替房东出租房子");
        rent.RentOut();
        getMoney();
    }

    public void showHouse(){
        System.out.println("带领客户参观房子");
    }

    public void getMoney(){
        System.out.println("收租金");
    }
}
