package com.github.tangyi.user.synchrodata.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "synch.role.api")
@Configuration
@Data
public class SynchMainRoleProperties {

    private String getListByPage = "http://121.36.89.139/idaas/manage/open-api/pmi/role/page?page=%s&rows=%s";
    private String add =  "http://121.36.89.139/idaas/manage/open-api/pmi/role/";
    private String update =  "http://121.36.89.139/idaas/manage/open-api/pmi/role/%s";
    private String del =  "http://121.36.89.139//idaas/manage/open-api/pmi/role/%s";
}
