package com.github.tangyi.webcast.api.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.account.LiveListAccountDetailResponse;

@Data
@Accessors(chain = true)
@ApiModel("直播频道详情信息")
public class LiveChannelDetailWithPosterUrl extends LiveListAccountDetailResponse.LiveChannelDetail {

    /**
     * 海报地址
     */
    @ApiModelProperty(name = "posterUrl", value = "海报地址")
    private String posterUrl;

    /**
     * 企业编号
     */
    @ApiModelProperty(name = "deptId", value = "企业编号")
    private Long deptId;

    /**
     * 企业名称
     */
    @ApiModelProperty(name = "deptName", value = "企业名称")
    private String deptName;

    /**
     * 主持人
     */
    @ApiModelProperty(name = "publisher", value = "主持人")
    private String publisher;

}