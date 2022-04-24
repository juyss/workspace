package com.github.tangyi.user.synchrodata.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "synch.dept")
@Configuration
@Data
public class SynchDeptProperties {

//    private String hwZtDeptUrl = "https://119.3.217.8:1443/oa/department";//华为中台 获以部门数据的地址
//    private String header1 = "X-HW-ID";
//    private String header1Value = "xunhuanhuagong__io.huawei.campusAI";
//    private String header2 = "X-HW-APPKEY";
//    private String header2Value = "BD6Ck0du/+EFI75iSrjMwA==";

    private String getTree = "http://121.36.89.139/idaas/manage/open-api/pmi/org/tree";
    private String getPage = "http://121.36.89.139/idaas/manage/open-api/pmi/org/page?page=%s&rows=%s&parentId=%s";
}
