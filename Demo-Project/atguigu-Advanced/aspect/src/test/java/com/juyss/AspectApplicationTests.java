package com.juyss;

import com.juyss.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AspectApplicationTests {

    @Autowired
    private UserService service;

    @Test
    public void Test(){
        service.service1();
        System.out.println("************************************************************************************************");
        service.service2();
        System.out.println("************************************************************************************************");
        service.service3("方法参数");
        System.out.println("************************************************************************************************");
    }

}
