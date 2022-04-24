package com.juyss.demo.service;

import com.juyss.demo.annotation.MyPointCut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: OrderService
 * @package com.juyss.demo.service
 * @project demo
 * @date 2022/1/27 17:18
 */
@Service
public class OrderService {

    @Autowired
    @Lazy
    private OrderService orderService;

    @MyPointCut
    public void test1(){
        System.out.println("OrderService test1");
        test2();
        orderService.test2();
    }

    @MyPointCut
    public void test2(){
        System.out.println("OrderService test2");
    }
}
