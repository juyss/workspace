package com.juyss.vo.resp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserInfoRespVO
 * @Desc: 用户信息
 * @package com.juyss.vo.resp
 * @project manager
 * @date 2021/1/12 17:55
 */
@Data
public class UserInfoRespVO {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "账号")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "昵称")
    private String nickName;

    @ApiModelProperty(value = "真实姓名")
    private String realName;

    @ApiModelProperty(value = "所属机构id")
    private String deptId;

    @ApiModelProperty(value = "所属机构名称")
    private String deptName;

}