package com.juyss.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserRoleOperationReqVO
 * @Desc: 用户角色
 * @package com.juyss.vo.req
 * @project manager
 * @date 2021/1/12 17:52
 */
@Data
public class UserRoleOperationReqVO {

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空")
    private String userId;

    @ApiModelProperty(value = "角色id集合")
    @NotEmpty(message = "角色id集合不能为空")
    private List<String> roleIds;
}
