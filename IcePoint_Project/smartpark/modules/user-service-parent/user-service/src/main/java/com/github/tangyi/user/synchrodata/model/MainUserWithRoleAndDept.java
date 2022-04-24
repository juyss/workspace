package com.github.tangyi.user.synchrodata.model;

import com.github.tangyi.common.security.ty.MainUserWithRole;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 主系统用户数据  包含角色和部门数据
 *  "orgs": [
 *         {
 *             "orgId": 97,
 *                 "orgName": "市场监督管理局质监处",
 *                 "orgCode": "",
 *                 "parentId": 74,
 *                 "orderNo": 5,
 *                 "note": null
 *         }
 *         ]
 */
@Data
public class MainUserWithRoleAndDept extends MainUserWithRole {
    private List<Org> orgs;

    @Data
    public static class Org implements Serializable {
        private static final long serialVersionUID = 2222222222222222L;
        private String orgName;
        private String orgCode;
        private String note;
        private Integer orgId;
        private Integer parentId;
        private Integer orderNo;

    }


}
