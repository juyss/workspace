package com.juyss.demo.service;

import com.juyss.demo.annotation.MyPointCut;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserService
 * @package com.juyss.demo.service
 * @project demo
 * @date 2022/1/27 17:18
 */
@Service
@Data
public class UserService {

    private final OrderService orderService;

    @MyPointCut
    public void test1(){
        System.out.println("UserService test1");
        orderService.test1();
    }
}
