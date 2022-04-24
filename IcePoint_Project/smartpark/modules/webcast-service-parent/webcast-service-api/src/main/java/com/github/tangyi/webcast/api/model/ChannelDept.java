package com.github.tangyi.webcast.api.model;

import com.github.tangyi.common.core.persistence.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 直播频道-部门关系
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChannelDept extends BaseEntity<ChannelDept> {


    private Long channelId;//频道ID

    private Long deptId;//部门ID

}
