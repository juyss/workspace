package com.github.tangyi.message.controller;

import cn.hutool.core.util.ArrayUtil;
import com.github.tangyi.common.core.constant.CommonConstant;
import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.IdGen;
import com.github.tangyi.common.log.annotation.Log;
import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.message.entity.AutoReply;
import com.github.tangyi.message.entity.LeaveMessage;
import com.github.tangyi.message.entity.ReplyMessage;
import com.github.tangyi.message.service.AutoReplyService;
import com.github.tangyi.message.service.LeaveMessageService;
import com.github.tangyi.message.service.ReplyMessageService;
import com.github.tangyi.msc.api.dto.SmsDto;
import com.github.tangyi.msc.api.feign.MscServiceClient;
import com.github.tangyi.msc.api.model.SmsResponse;
import info.debatty.java.stringsimilarity.Levenshtein;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LeaveMessageController
 * @Desc: 留言Controller
 * @package com.github.tangyi.message
 * @project park
 * @date 2021/3/27 22:41
 */
@Api("留言信息管理")
@Slf4j
@RestController
@RequestMapping("/v1/leave_msg")
public class LeaveMessageController {

    @Autowired
    private LeaveMessageService leaveMessageService;

    @Autowired
    private ReplyMessageService replyMessageService;

    @Autowired
    private AutoReplyService autoReplyService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MscServiceClient mscServiceClient;

    /**
     * redis默认键的格式，Key格式：gitee:PublicService:VerifyCode:830098782938157056用户手机号，value数据：验证码
     */
    private final String redisKey = "gitee:PublicService:VerifyCode:";

    /**
     * 发送短信验证码服务
     *
     * @param phoneNum 要发送验证码的手机号
     * @return 响应对象
     */
    @ApiOperation(value = "发送短信验证码", notes = "发送短信验证码")
    @ApiImplicitParam(name = "phoneNum", value = "要发送短信的手机号", required = true, dataType = "String")
    @Log("发送短信验证码")
    @PostMapping("/sendVerifyCode")
    public ResponseBean<Object> sendVerifyCodeSms(@RequestParam("phoneNum") String phoneNum) {

        //获取随机验证码
        String verifyCode = String.valueOf(IdGen.snowflakeId()).substring(12, 18);

        //发送包含验证码的短信---TODO
        SmsDto smsDto = new SmsDto();

        //短信内容
        String smsContent = "您正在进行留言操作，请输入验证码进行验证，有效期三分钟。验证码为：";
        smsDto.setContent(smsContent + verifyCode);
        smsDto.setReceiver(phoneNum);
        //调用短信发送服务
        ResponseBean<?> smsResp = mscServiceClient.sendSms(smsDto);

        //将验证码存到Redis中
        redisTemplate.opsForValue().set(redisKey + phoneNum, verifyCode, 60 * 3, TimeUnit.SECONDS);

        //包装响应对象
        ResponseBean<Object> response = new ResponseBean<>();
        response.setCode(200);
        response.setMsg("success");
        response.setData(smsResp.getData());
        return response;
    }

    /**
     * @param phoneNum       用户手机号
     * @param userVerifyCode 输入的验证码
     * @return 响应对象
     */
    @ApiOperation(value = "对用户输入的验证码进行验证", notes = "对用户输入的验证码进行验证")
    @ApiImplicitParams({@ApiImplicitParam(name = "phoneNum", value = "用户输入的手机号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userVerifyCode", value = "用户输入的验证码", required = true, dataType = "String")})
    @Log("对用户输入的验证码进行验证")
    @PostMapping("/verify")
    public ResponseBean<String> verify(@RequestParam("phoneNum") String phoneNum, @RequestParam("userVerifyCode") String userVerifyCode) {

        ResponseBean<String> response = new ResponseBean<>();

        //从Redis中查询验证码
        String verifyCode = (String) redisTemplate.opsForValue().get(redisKey + phoneNum);

        //判断是否获取到验证码
        if (verifyCode != null && verifyCode.equals(userVerifyCode)) {
            //获取到value,并且相等,验证成功
            response.setCode(200);
            response.setMsg("success");
            response.setData(phoneNum);
        } else {
            //没有获取到value
            response.setCode(500);
            response.setMsg("failed");
            response.setData("验证码错误");
        }
        return response;
    }

    /**
     * 添加一条留言
     *
     * @param leaveMessage 留言封装对象
     * @return 响应对象
     */
    @ApiOperation(value = "创建留言", notes = "创建留言")
    @ApiImplicitParam(name = "leaveMessage", value = "留言实体leaveMessage", required = true, dataType = "LeaveMessage")
    @Log("新增留言")
    @PostMapping("/insert")
    public ResponseBean<LeaveMessage> insert(@RequestBody LeaveMessage leaveMessage) {

        //生成随机ID
        leaveMessage.setId(IdGen.snowflakeId());

        //暂时设置审核状态为：通过
        leaveMessage.setCheckStatus(1);

        //默认设置回复状态为：未回复
        leaveMessage.setReplyStatus(0);

        //默认是设置删除状态为：未删除
        leaveMessage.setDelFlag(0);

        //数据库插入数据
        int leaveMessageInsert = leaveMessageService.insert(leaveMessage);

        //获取自动回复
        ReplyMessage autoReply = getAutoReply(leaveMessage);

        int autoReplyInsert = 0;
        if(autoReply.getId() != null){
            //将回复添加到数据库
            autoReplyInsert = replyMessageService.insertReply(autoReply);
            //更新留言回复状态
            leaveMessageService.updateReplyStatus(leaveMessage.getId(), 1);
            leaveMessage.setReplyMessage(autoReply);
        }

        //设置响应对象
        ResponseBean<LeaveMessage> responseBean = new ResponseBean<>();
        if (leaveMessageInsert == 1 && autoReplyInsert == 1) {
            responseBean.setCode(200);
            responseBean.setData(leaveMessage);
            responseBean.setMsg("success");
        } else {
            responseBean.setCode(500);
            responseBean.setData(leaveMessage);
            responseBean.setMsg("failed");
        }
        return responseBean;
    }

    /**
     * 根据ID删除留言及其回复
     * @param ids 留言主键ID字符串
     * @return 删除信息的数量
     */
    @ApiOperation(value = "批量删除留言", notes = "删除多条留言，返回删除数量")
    @ApiImplicitParam(name = "ids", value = "留言主键ID字符串", required = true, dataType = "String")
    @Log("删除留言及其回复")
    @DeleteMapping("/deleteById")
    public ResponseBean<HashMap<String, Integer>> deleteById(@RequestParam("ids") String ids){
        String[] split = ids.split(",");
        ArrayList<Long> list = new ArrayList<>();
        for (String id : split) {
            list.add(Long.valueOf(id));
        }

        //删除留言
        int messageDeleted = leaveMessageService.deleteById(list);

        //删除回复
        int replyDeleted = replyMessageService.deleteByMessageId(list);

        ResponseBean<HashMap<String, Integer>> responseBean = new ResponseBean<>();
        responseBean.setCode(200);
        responseBean.setMsg("success");

        //将删除的条数返回
        HashMap<String, Integer> map = new HashMap<>();
        map.put("messageDeleted",messageDeleted);
        map.put("replyDeleted",replyDeleted);
        responseBean.setData(map);

        return responseBean;
    }


    /**
     * 根据留言ID更新审核状态
     *
     * @param id          留言ID
     * @param checkStatus 留言审核状态
     * @return 响应对象
     */
    @ApiOperation(value = "更新留言审核状态", notes = "更新留言审核状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "留言主键ID", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "checkStatus", value = "留言审核状态：0:未通过、1:通过", required = true, dataType = "Integer")
    })
    @Log("更新留言审核状态")
    @PutMapping("/update_check_status")
    public ResponseBean<LeaveMessage> updateCheckStatus(@RequestParam("id") Long id, @RequestParam("checkStatus") Integer checkStatus) {
        int update = leaveMessageService.updateCheckStatus(id, checkStatus);
        ResponseBean<LeaveMessage> responseBean = new ResponseBean<>();
        if (update == 1) {
            responseBean.setCode(200);
            responseBean.setMsg("success");
        } else {
            responseBean.setCode(500);
            responseBean.setMsg("failed");
        }
        return responseBean;
    }

    /**
     * 根据留言ID获取留言信息及回复信息
     *
     * @param id 留言主键ID
     * @return 响应对象
     */
    @ApiOperation(value = "根据留言ID获取一条留言及其回复信息", notes = "根据留言ID获取一条留言及其回复信息")
    @ApiImplicitParam(name = "id", value = "留言主键ID", required = true, dataType = "Long")
    @Log("根据留言ID获取一条留言及其回复信息")
    @GetMapping("/get_msg_info_by_id")
    public ResponseBean<HashMap<String, Object>> getMsgInfo(@RequestParam Long id) {

        //根据留言主键ID查询留言信息
        LeaveMessage leaveMessage = leaveMessageService.getMessageById(id);

        //根据留言ID查询回复信息
        List<ReplyMessage> replyMessageList = replyMessageService.getReplyByMessageId(id);

        //放入map
        HashMap<String, Object> map = new HashMap<>();
        map.put("leaveMessage", leaveMessage);
        map.put("replyMessage", replyMessageList);

        //封装响应信息
        ResponseBean<HashMap<String, Object>> responseBean = new ResponseBean<>();
        responseBean.setCode(200);
        responseBean.setData(map);
        responseBean.setMsg("success");
        return responseBean;
    }


    /**
     * 按条件查询留言及回复信息，并进行分页
     *
     * @param pageNum     页码
     * @param pageSize    页面大小
     * @param sort        排序分类
     * @param order       排序方向
     * @param type        留言类型 1:咨询、2:建议、3:投诉、4:分享
     * @param createDate  创建时间
     * @param checkStatus 审核状态 1:通过、0:不通过
     * @param replyStatus 回复状态 1:已受理、0:未受理
     * @return 封装分页信息、留言信息、回复信息
     */
    @ApiOperation("按条件查询留言及回复信息，不添加任何条件时查询所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Integer"),
            @ApiImplicitParam(name = "sort", value = "排序字段", dataType = "String"),
            @ApiImplicitParam(name = "order", value = "排序方向", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "留言类型", dataType = "Integer"),
            @ApiImplicitParam(name = "createDate", value = "留言创建时间", dataType = "Date"),
            @ApiImplicitParam(name = "checkStatus", value = "留言审核状态：0:未通过、1:已通过", dataType = "Integer"),
            @ApiImplicitParam(name = "replyStatus", value = "留言回复状态：0:未回复、1:已回复", dataType = "Integer")
    })
    @GetMapping("/get_msg_info_list")
    public PageResult getMsgInfoList(@RequestParam(value = CommonConstant.PAGE_NUM, required = false, defaultValue = CommonConstant.PAGE_NUM_DEFAULT) Integer pageNum,
                                     @RequestParam(value = CommonConstant.PAGE_SIZE, required = false, defaultValue = CommonConstant.PAGE_SIZE_DEFAULT) Integer pageSize,
                                     @RequestParam(value = CommonConstant.SORT, required = false, defaultValue = CommonConstant.PAGE_SORT_DEFAULT) String sort,
                                     @RequestParam(value = CommonConstant.ORDER, required = false, defaultValue = CommonConstant.PAGE_ORDER_DEFAULT) String order,
                                     @RequestParam(value = "type", required = false) Integer type,
                                     @RequestParam(value = "createDate", required = false) Date createDate,
                                     @RequestParam(value = "checkStatus", required = false) Integer checkStatus,
                                     @RequestParam(value = "replyStatus", required = false) Integer replyStatus
    ) {
        //根据条件查询留言信息
        PageResult pageResult = leaveMessageService.getListByCondition(pageNum, pageSize, type, createDate, checkStatus, replyStatus, sort, order);
        List<Map<String, Object>> messageList = (List<Map<String, Object>>) pageResult.getRows();

        //为每一条留言信息添加回复信息
        for (Map<String, Object> message : messageList) {

            //获取回复状态
            Integer isReply = (Integer) message.get("reply_status");

            //如果状态为已回复，就查询回复信息添加进Map
            if (isReply == 1) {
                Long id = (Long) message.get("id");
                List<ReplyMessage> replyMessageList = replyMessageService.getReplyByMessageId(id);
                message.put("replyMessage", replyMessageList);
            }
        }
        return pageResult;
    }

    /**
     * 获取留言统计信息
     * @return 返回不同留言类型的留言数量
     */
    @ApiOperation("获取留言统计信息")
    @GetMapping("/getStaticInfo")
    public ResponseBean<List<Map<Object, Object>>> getStaticInfo(){
        //获取统计信息
        List<Map<Object, Object>> staticInfo = leaveMessageService.getStaticInfo();

        //创建请求响应
        ResponseBean<List<Map<Object, Object>>> bean = new ResponseBean<>();
        bean.setData(staticInfo);
        bean.setMsg("success");
        bean.setCode(200);
        return bean;
    }

    /**
     * 使用分词和字符串相似度获取自动回复内容
     * @param leaveMessage 留言
     * @return
     */
    private ReplyMessage getAutoReply(LeaveMessage leaveMessage){

        //获取留言内容
        String text=leaveMessage.getMessageContent();

        StringReader reader= null;
        TokenStream ts= null;
        StringBuilder sb = new StringBuilder();
        ReplyMessage replyMessage = new ReplyMessage();
        try {
            //创建分词对象
            Analyzer anal=new IKAnalyzer(true);
            reader = new StringReader(text);
            //分词
            ts = anal.tokenStream("", reader);
            ts.reset();
            CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
            //遍历分词数据
            while(ts.incrementToken()){
                if (term !=null && term.toString().length()>1){

                    //将长度大于1的字符串连接成一个新的字符串
                    sb.append(term);
                }
            }

            //获取所有的自动回复数据
            PageResult pageResult = autoReplyService.queryAll(Integer.parseInt(CommonConstant.PAGE_NUM_DEFAULT),null,
                    Integer.MAX_VALUE,
                    CommonConstant.PAGE_SORT_DEFAULT,
                    CommonConstant.PAGE_ORDER_DEFAULT);
            List<AutoReply> autoReplyList = (List<AutoReply>) pageResult.getRows();

            AutoReply bestMatchAutoReply = new AutoReply();
            double bestMatchDistance = 1.0; //1.0为不相似

            for (AutoReply autoReply : autoReplyList) {
                String autoReplyTheme = autoReply.getAutoReplyTheme();
                NormalizedLevenshtein levenshtein = new NormalizedLevenshtein();
                double distance = levenshtein.distance(autoReplyTheme, sb.toString()); //获取相似度，越小越好
                if (distance < bestMatchDistance){ //如果这个值比最合适的还小
                    bestMatchAutoReply = autoReply; //就把这个值对应的自动回复赋值给最佳匹配回复
                    bestMatchDistance = distance; //同时修改最合适的值
                }
            }

            //如果有匹配结果
            if (bestMatchDistance != 1.0 && bestMatchAutoReply.getId() != null ) {
                //创建新的回复对象
                replyMessage.setId(IdGen.snowflakeId());
                replyMessage.setMessageId(leaveMessage.getId());
                replyMessage.setReplyContent(bestMatchAutoReply.getAutoReplyContent());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null & ts !=null) {
                try {
                    ts.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                reader.close();
            }
        }

        return replyMessage;
    }

}
