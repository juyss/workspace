package com.juyss.vo.resp;

import com.juyss.entity.SysRole;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserOwnRoleRespVO
 * @Desc: 用户角色
 * @package com.juyss.vo.resp
 * @project manager
 * @date 2021/1/12 17:55
 */
@Data
public class UserOwnRoleRespVO {

    @ApiModelProperty("所有角色集合")
    private List<SysRole> allRole;

    @ApiModelProperty(value = "用户所拥有角色集合")
    private List<String> ownRoles;
}
