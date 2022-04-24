package com.github.tangyi.message.controller;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.IdGen;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.message.entity.AutoReply;
import com.github.tangyi.message.entity.LeaveMessage;
import com.github.tangyi.message.service.AutoReplyService;
import com.github.tangyi.user.api.feign.UserServiceClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName AutoReplyController
 * @description
 * @since 2021-05-19 10:49
 */
@Api("自动回复管理")
@Slf4j
@RestController
@RequestMapping("/autoReply")
public class AutoReplyController {

    @Autowired
    private AutoReplyService autoReplyService;

    @Autowired
    private UserServiceClient userServiceClient;

    /**
     * 添加
     * @param autoReply
     * @return
     */
    @ApiOperation(value = "创建自动回复", notes = "创建自动回复")
    @ApiImplicitParam(name = "autoReply", value = "自动回复实体autoReply", required = true, dataType = "AutoReply")
    @Log("新增自动回复")
    @PostMapping("/insert")
    public ResponseBean<AutoReply> insert(@RequestBody AutoReply autoReply){

        //设置主键
        autoReply.setId(IdGen.snowflakeId());

        //设置回复人名字
        String replyUserName = getCurrentUser().getData().getName();
        autoReply.setCreateUserName(replyUserName);

        int insert = autoReplyService.insert(autoReply);

        //设置响应对象
        ResponseBean<AutoReply> responseBean = new ResponseBean<>();
        if (insert == 1) {
            responseBean.setCode(200);
            responseBean.setData(autoReply);
            responseBean.setMsg("success");
        } else {
            responseBean.setCode(500);
            responseBean.setMsg("failed");
        }
        return responseBean;
    }

    /**
     * 批量删除
     * @param ids id数组
     * @return
     */
    @ApiOperation(value = "批量删除", notes = "删除多条自动回复")
    @ApiImplicitParam(name = "ids", value = "留言主键ID字符串，用逗号隔开", required = true, dataType = "String")
    @Log("删除留言及其回复")
    @DeleteMapping("/deleteByIds")
    public ResponseBean<Object> deleteById(@RequestParam("ids") String ids){
        String[] split = ids.split(",");
        ArrayList<Long> list = new ArrayList<>();
        for (String id : split) {
            list.add(Long.valueOf(id));
        }

        boolean delete = autoReplyService.deleteByIds(list);

        //设置响应对象
        ResponseBean<Object> responseBean = new ResponseBean<>();
        if (delete) {
            responseBean.setCode(200);
            responseBean.setMsg("success");
        } else {
            responseBean.setCode(500);
            responseBean.setMsg("failed");
        }
        return responseBean;
    }

    /**
     * 更新自动回复
     * @param autoReply
     * @return
     */
    @ApiOperation(value = "更新自动回复", notes = "更新自动回复")
    @ApiImplicitParam(name = "autoReply", value = "新的自动回复对象", required = true, dataType = "AutoReply")
    @Log("删除留言及其回复")
    @PostMapping("/update")
    public ResponseBean<AutoReply> update(@RequestBody AutoReply autoReply){

        int update = autoReplyService.update(autoReply);

        //设置响应对象
        ResponseBean<AutoReply> responseBean = new ResponseBean<>();
        if (update == 1) {
            responseBean.setCode(200);
            responseBean.setData(autoReply);
            responseBean.setMsg("success");
        } else {
            responseBean.setCode(500);
            responseBean.setMsg("failed");
        }
        return responseBean;
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @ApiOperation(value = "查询自动回复", notes = "查询自动回复")
    @ApiImplicitParam(name = "id", value = "要查询的id", required = true, dataType = "Long")
    @Log("删除留言及其回复")
    @GetMapping("/selectById")
    public ResponseBean<AutoReply> selectById(@RequestParam("id") Long id){
        return new ResponseBean<>(autoReplyService.queryById(id));
    }

    /**
     * 查询所有并分页
     * @param pageNum
     * @param pageSize
     * @param sort
     * @param order
     * @return
     */
    @ApiOperation("查询所有并分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Integer"),
            @ApiImplicitParam(name = "sort", value = "排序字段", dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方向", dataType = "String")
    })
    @GetMapping("/selectAll")
    public PageResult selectAll(@RequestParam(value = "autoReplyTheme",required = false)String name,
                                @RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order
    ){
        return autoReplyService.queryAll(pageNum, name, pageSize, sort, order);
    }

    /**
     * 获取当前登录的用户信息
     * @return 数据封装
     */
    private ResponseBean<UserVo> getCurrentUser() {
        String user = SysUtil.getUser();
        String tenantCode = SysUtil.getTenantCode();
        return userServiceClient.findUserByIdentifier(user, tenantCode);
    }
}
