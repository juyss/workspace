package com.juyss.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LoginRespVO
 * @Desc: 登陆响应
 * @package com.juyss.vo.resp
 * @project manager
 * @date 2021/1/12 17:54
 */
@Data
public class LoginRespVO {

    @ApiModelProperty(value = "token")
    private String accessToken;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "用户所拥有的菜单权限(前后端分离返回给前端控制菜单和按钮的显示和隐藏)")
    private List<PermissionRespNode> list;
}
