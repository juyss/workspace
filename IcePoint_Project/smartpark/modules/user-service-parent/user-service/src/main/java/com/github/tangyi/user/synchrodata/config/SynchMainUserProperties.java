package com.github.tangyi.user.synchrodata.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "synch.user.api")
@Configuration
@Data
public class SynchMainUserProperties {

    private String getListByPage = "http://121.36.89.139/idaas/manage/open-api/pmi/user/page?page=%s&rows=%s";
    private String getItemById = "http://121.36.89.139/idaas/manage/open-api/pmi/user/%s";
    //保留密码的用户们（以方便从原始登陆界面登陆系统作调试和测试）
    private String keepPasswordUsers = "admin,yinxiaolin,suyanchun,zhouda,superadmin";
}
