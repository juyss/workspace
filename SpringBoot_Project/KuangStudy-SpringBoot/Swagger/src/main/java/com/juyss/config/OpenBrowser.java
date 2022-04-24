package com.juyss.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author ShmeBluk
 * @version 1.0
 * @ClassName: OpenBrowser
 * @Desc:
 * @package com.juyss.config
 * @project KuangStudy-SpringBoot
 * @date 2020/10/24 10:56
 */
@Component
public class OpenBrowser implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("开始自动加载指定的页面");
        try {
            Runtime.getRuntime().exec("cmd   /c   start   http://localhost:8080/swg");//可以指定自己的路径
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
