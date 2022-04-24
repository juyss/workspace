package com.github.tangyi.webcast.api.dto.req;

import io.swagger.annotations.ApiModelProperty;
import net.polyv.live.v1.entity.channel.operate.LiveChannelRequest;

public class LiveChannelWithDeptRequest extends LiveChannelRequest {

    @ApiModelProperty(name = "deptId", value = "部门ID", required = true)
    private Long deptId;

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }
}
