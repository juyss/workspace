package com.github.tangyi.pub.api.dto.office;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "移动节点参数实体")
public class MoveIdxDto {
    @ApiModelProperty("需要移动id")
    private Long id ;

    @ApiModelProperty("需要移动下标")
    private Double idx;

    @ApiModelProperty("被移动id")
    private Long byId;

    @ApiModelProperty("被移动下标")
    private Double byIdx;

    @ApiModelProperty("移动类型")
    private  String type;



}
