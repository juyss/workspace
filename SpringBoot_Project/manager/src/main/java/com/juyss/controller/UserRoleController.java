package com.juyss.controller;

import com.juyss.common.aop.annotation.LogAnnotation;
import com.juyss.common.utils.DataResult;
import com.juyss.service.UserRoleService;
import com.juyss.vo.req.UserRoleOperationReqVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: UserRoleController
 * @Desc:
 * @package com.juyss.controller
 * @project manager
 * @date 2021/1/21 14:44
 */
@RequestMapping("/sys")
@RestController
@Api(tags = "组织管理-用户和角色关联接口")
public class UserRoleController {
    @Resource
    private UserRoleService userRoleService;

    @PostMapping("/user/role")
    @ApiOperation(value = "修改或者新增用户角色接口")
    @LogAnnotation(title = "用户和角色关联接口", action = "修改或者新增用户角色")
    public DataResult operationUserRole(@RequestBody @Valid UserRoleOperationReqVO vo) {
        userRoleService.addUserRoleInfo(vo);
        return DataResult.success();
    }
}
