package com.github.tangyi.common.core.model.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "BaseReq", description = "接收管理后台通用查询参数")
public class BaseReq {

    @ApiModelProperty(value = "搜索关键字", dataType = "String")
    protected String kw;
    @ApiModelProperty(value = "开始时间(yyyy-MM-dd HH:mm:ss)", dataType = "Date")
    protected Date startTime;
    @ApiModelProperty(value = "结束时间(yyyy-MM-dd HH:mm:ss)", dataType = "Date")
    protected Date endTime;
}
