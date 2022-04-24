package com.icepoint.framework.code;

import com.icepoint.framework.code.utils.create.AbsCreateProjectTemplate;
import com.icepoint.framework.code.utils.create.TestCaseTemplate;
import com.icepoint.framework.web.system.service.ProjectService;
import com.icepoint.framework.web.system.service.ParameterService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ck
 * @version 1.0
 * @date 2021/5/26 10:36
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Autowired
    AbsCreateProjectTemplate createBootProject;

    @Autowired
    ProjectService projectService;

    @Autowired
    TestCaseTemplate testCaseTemplate;

    @Autowired
    ParameterService parameterService;

    @org.junit.Test
    public void test() {
        createBootProject.creat(1L);
    }


//    @org.junit.Test
//    public void test1(){
//        SysProject sysProject = sysProjectService.findOne(51000000025l);
//        testCaseTemplate.testCaseCommen(sysProject);
//    }
//
//    @org.junit.Test
//    public void test2(){
//        String s = tParameterService.groupDescription(51000000025l);
//    }

}
