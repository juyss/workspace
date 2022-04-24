package com.juyss.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: HomeRespVO
 * @Desc:
 * @package com.juyss.vo.resp
 * @project manager
 * @date 2021/1/12 17:53
 */
@Data
public class HomeRespVO {

    @ApiModelProperty(value = "用户信息")
    private UserInfoRespVO userInfo;

    @ApiModelProperty(value = "目录菜单")
    private List<PermissionRespNode> menus;

}