package com.juyss.vo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: RolePermissionOperationReqVO
 * @Desc: 角色权限
 * @package com.juyss.vo.req
 * @project manager
 * @date 2021/1/12 17:51
 */
@Data
public class RolePermissionOperationReqVO {

    @ApiModelProperty(value = "角色id")
    @NotBlank(message = "角色id不能为空")
    private String roleId;

    @ApiModelProperty(value = "菜单权限集合")
    @NotEmpty(message = "菜单权限集合不能为空")
    private List<String> permissionIds;
}
