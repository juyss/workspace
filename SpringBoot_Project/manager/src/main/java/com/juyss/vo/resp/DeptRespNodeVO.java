package com.juyss.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: DeptRespNodeVO
 * @Desc: 部门节点响应
 * @package com.juyss.vo.resp
 * @project manager
 * @date 2021/1/12 17:52
 */
@Data
public class DeptRespNodeVO {

    @ApiModelProperty(value = "组织id")
    private String id;

    @ApiModelProperty(value = "组织编码")
    private String deptNo;

    @ApiModelProperty(value = "组织名称")
    private String title;

    @ApiModelProperty(value = "组织名称")
    private String label;

    @ApiModelProperty(value = "组织父级id")
    private String pid;

    @ApiModelProperty(value = "组织状态")
    private Integer status;

    @ApiModelProperty(value = "组织关系id")
    private String relationCode;

    @ApiModelProperty(value = "是否展开 默认不展开(false)")
    private boolean spread = true;

    @ApiModelProperty(value = "是否选中")
    private boolean checked = false;

    private boolean disabled = false;

    @ApiModelProperty(value = "子集")
    private List<?> children;

    public String getLabel() {
        return title;
    }

}

