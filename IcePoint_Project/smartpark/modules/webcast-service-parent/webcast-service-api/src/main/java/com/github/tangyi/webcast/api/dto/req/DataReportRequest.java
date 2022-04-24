package com.github.tangyi.webcast.api.dto.req;

import com.github.tangyi.webcast.api.model.DataReport;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.Min;

/**
 * 数据报表
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class DataReportRequest extends DataReport {

    @ApiModelProperty(name = "channelId", value = "频道ID", required = false)
    private String channelId;

    @ApiModelProperty(name = "name", value = "直播名称", required = false)
    private String name;

    @ApiModelProperty(name = "startDate", value = "开始日期", required = false)
    private String startDate;

    @ApiModelProperty(name = "endDate", value = "结束日期", required = false)
    private String endDate;

    @ApiModelProperty(name = "pageNum", value = "页数，默认为1", dataType = "Integer", example = "1", required = false)
    private Integer pageNum = 1;

    @ApiModelProperty(name = "pageSize", value = "每页显示的数据条数，默认每页显示10条数据", dataType = "Integer", example = "12", required = false)
    @Min(value = 0, message = "每页显示的数据条数不能小于0")
    private Integer pageSize = 10;

}
