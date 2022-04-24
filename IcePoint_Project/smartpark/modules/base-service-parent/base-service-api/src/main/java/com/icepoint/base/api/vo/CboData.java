package com.icepoint.base.api.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CboData {

    // 招商政策完成率
    private Double policyCompletionRate;
    // 招商政策当月已审核数
    private Integer policyApproved;

    // 招商项目完成率
    private Double itemCompletionRate;
    // 招商项目当月已审核数
    private Integer itemApproved;

    // 招商动态完成率
    private Double dynamicCompletionRate;
    // 招商动态当月已审核数
    private Integer dynamicApproved;

    // 招商政策当月新增数
    private Long monthlyPolicy;
    // 招商项目当月新增数
    private Long monthlyItem;
    // 招商动态当月新增数
    private Long monthlyDynamic;

}
