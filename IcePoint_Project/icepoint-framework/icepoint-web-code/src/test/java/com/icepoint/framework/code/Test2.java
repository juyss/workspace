package com.icepoint.framework.code;


import com.icepoint.framework.code.sysfunction.service.SysFunctionService;
import com.icepoint.framework.code.utils.create.AbsCreateProjectTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ck
 * @version 1.0
 * @date 2021/5/28 13:41
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test2 {
    @Autowired
    AbsCreateProjectTemplate absCreateProjectTemplate;

    @Autowired
    SysFunctionService service;

    @Test
    public void test() {
        absCreateProjectTemplate.creat(51000000038L);
    }

    @Test
    public void test1() {
        //  QiNiuFileManager instance = QiNiuFileManager.getInstance();
        service.deleteProcessFunction(304l);
    }

}
