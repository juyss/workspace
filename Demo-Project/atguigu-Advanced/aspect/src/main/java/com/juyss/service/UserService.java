package com.juyss.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: UserService
 * @Desc: 业务类
 * @package com.juyss.service
 * @project atguigu-Advanced
 * @date 2020/12/7 15:08
 */
@Service
public class UserService {

    public void service1(){
        System.out.println("Service-1-执行");
    }

    public String service2(){
        System.out.println("Service-2-执行");
        //int i = 1/0;
        return "Success!!!";
    }

    public ArrayList<String> service3(String userName){
        System.out.println("Service-3-执行");
        ArrayList<String> list = new ArrayList<>();
        list.add("Service-3-执行成功!!!");
        list.add(userName);
        return list;
    }

}
