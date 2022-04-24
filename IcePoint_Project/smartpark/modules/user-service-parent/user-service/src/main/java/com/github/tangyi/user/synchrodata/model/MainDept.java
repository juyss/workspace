package com.github.tangyi.user.synchrodata.model;

import lombok.Data;

import java.util.List;

@Data
public class MainDept {

    private Integer orgId;//: 21,
    private Integer parentId;// 1,
    private String orgName;// "园区领导",
    private String orgCode;// "",
    private Integer orderNo;// null,
    private String note;// null,
    private Integer orgType;//  1=事业单位，2=企业。
    private List<MainDept> children;// null

}
