package com.github.tangyi.message.controller;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.IdGen;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.common.security.utils.SysUtil;
import com.github.tangyi.user.api.feign.UserServiceClient;
import com.github.tangyi.message.entity.ReplyMessage;
import com.github.tangyi.message.service.LeaveMessageService;
import com.github.tangyi.message.service.ReplyMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: ReplyMessageController
 * @Desc: 回复Controller
 * @package com.github.tangyi.message.controller
 * @project park
 * @date 2021/3/28 16:29
 */
@Api("回复信息管理")
@RestController
@RequestMapping("/v1/reply_msg")
public class ReplyMessageController {

    @Autowired
    private ReplyMessageService replyMessageService;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private LeaveMessageService leaveMessageService;


    /**
     * 添加一条留言回复
     * @param replyMessage 回复封装对象
     * @return 响应对象
     */
    @ApiOperation(value = "创建留言回复", notes = "创建留言回复")
    @ApiImplicitParam(name = "replyMessage", value = "留言实体ReplyMessage", required = true, dataType = "ReplyMessage")
    @Log("新增留言回复")
    @PostMapping("/insert_reply")
    public ResponseBean<ReplyMessage> insertReply(@RequestBody ReplyMessage replyMessage){

        //自动生成id
        replyMessage.setId(IdGen.snowflakeId());

        //设置回复人名字
        String replyUserName = getCurrentUser().getData().getName();
        replyMessage.setReplyUserName(replyUserName);

        //回复添加到数据库
        int insert = replyMessageService.insertReply(replyMessage);

        //更新留言的回复状态,设置为1，表示已回复
        leaveMessageService.updateReplyStatus(replyMessage.getMessageId(),1);

        //封装数据返回
        ResponseBean<ReplyMessage> responseBean = new ResponseBean<>();
        if (insert == 1) {
            responseBean.setCode(200);
            responseBean.setData(replyMessage);
            responseBean.setMsg("success");
        } else {
            responseBean.setCode(500);
            responseBean.setMsg("failed");
        }
        return responseBean;
    }

    @ApiOperation(value = "根据ID删除留言回复", notes = "根据ID删除留言回复")
    @ApiImplicitParams({@ApiImplicitParam(name = "messageId", value = "留言主键ID",required = true, dataType = "Long"),
                        @ApiImplicitParam(name = "replyId", value = "回复主键ID", required = true, dataType = "Long"),
                        @ApiImplicitParam(name = "isNeedUpdateMsgReplyStatus", value = "是否需要更新留言状态", required = true, dataType = "String")
    })
    @Log("删除留言回复")
    @DeleteMapping("/deleteById")
    public ResponseBean<ReplyMessage> deleteById(@RequestParam("messageId") Long messageId ,
                                                 @RequestParam("replyId")Long replyId,
                                                 @RequestParam("isNeedUpdateMsgReplyStatus")String isNeedUpdateMsgReplyStatus){

        int delete = replyMessageService.deleteById(replyId);

        //判断是否需要更新留言回复状态
        if("true".equals(isNeedUpdateMsgReplyStatus)){
            leaveMessageService.updateReplyStatus(messageId,0);
        }

        //封装数据返回
        ResponseBean<ReplyMessage> responseBean = new ResponseBean<>();
        if (delete == 1) {
            responseBean.setCode(200);
            responseBean.setMsg("success");
        } else {
            responseBean.setCode(500);
            responseBean.setMsg("failed");
        }
        return responseBean;
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
