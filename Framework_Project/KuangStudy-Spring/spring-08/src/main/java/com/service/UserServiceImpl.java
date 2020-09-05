package com.service;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserServiceImpl
 * @Desc: 被代理类
 * @package com.service
 * @project KuangStudy-Spring
 * @date 2020/9/4 20:55
 */
public class UserServiceImpl implements UserService{

    @Override
    public void insert() {
        System.out.println("insert()");
    }

    @Override
    public void delete() {
        System.out.println("delete()");
    }

    @Override
    public void update() {
        System.out.println("update()");
    }

    @Override
    public void select() {
        System.out.println("select()");
    }

}
