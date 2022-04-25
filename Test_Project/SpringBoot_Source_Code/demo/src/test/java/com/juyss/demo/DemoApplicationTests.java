package com.juyss.demo;

import com.juyss.demo.service.OrderService;
import com.juyss.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testAop(){
        userService.test1();
        System.out.println("=================");
        orderService.test1();
    }
}
