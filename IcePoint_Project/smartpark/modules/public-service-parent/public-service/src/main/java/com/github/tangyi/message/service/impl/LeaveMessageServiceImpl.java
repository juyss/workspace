package com.github.tangyi.message.service.impl;

import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.core.mybatis.page.PageUtils;
import com.github.tangyi.message.entity.LeaveMessage;
import com.github.tangyi.message.enums.MessageType;
import com.github.tangyi.message.mapper.LeaveMessageMapper;
import com.github.tangyi.message.service.LeaveMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LeaveMessageServiceImpl
 * @Desc: 留言Service层接口实现类
 * @package com.github.tangyi.message.service.impl
 * @project park
 * @date 2021/3/27 15:55
 */
@Service
public class LeaveMessageServiceImpl implements LeaveMessageService {

    @Autowired
    private LeaveMessageMapper leaveMessageMapper;

    /**
     * 添加一条留言
     *
     * @param message 封装的留言对象
     * @return 添加成功的数量
     */
    @Override
    public int insert(LeaveMessage message) {
        return leaveMessageMapper.insert(message);
    }

    /**
     * 根据ID删除留言
     *
     * @param ids 留言主键ID集合
     * @return 删除成功的条数
     */
    @Override
    public int deleteById(List<Long> ids) {
        return leaveMessageMapper.deleteById(ids);
    }

    /**
     * 根据ID（主键）更新留言审核状态
     *
     * @param id          id主键
     * @param checkStatus 审核状态
     * @return 修改成功的条数
     */
    @Override
    public int updateCheckStatus(Long id, Integer checkStatus) {
        return leaveMessageMapper.updateCheckStatus(id, checkStatus);
    }

    /**
     * 根据ID（主键）更新留言回复状态
     *
     * @param id          主键
     * @param replyStatus 回复状态
     * @return 更新成功的条数
     */
    @Override
    public int updateReplyStatus(Long id, Integer replyStatus) {
        return leaveMessageMapper.updateReplyStatus(id, replyStatus);
    }

    /**
     * 根据ID（主键）获取留言信息
     *
     * @param id id主键
     * @return 符合条件的留言
     */
    @Override
    public LeaveMessage getMessageById(Long id) {
        return leaveMessageMapper.getMessageById(id);
    }

    /**
     * 根据条件查询并排序
     *
     * @param pageNum     当前页码
     * @param pageSize    页面大小
     * @param type        留言类型
     * @param createDate  创建时间
     * @param checkStatus 审核状态
     * @param replyStatus 回复状态
     * @param sort        排序分类
     * @param order       排序方向
     * @return 留言集合
     */
    @Override
    public PageResult getListByCondition(Integer pageNum,
                                         Integer pageSize,
                                         Integer type,
                                         Date createDate,
                                         Integer checkStatus,
                                         Integer replyStatus,
                                         String sort,
                                         String order) {
        return PageUtils.query(pageNum, pageSize, 10, () -> leaveMessageMapper.getListByCondition(type, createDate, checkStatus, replyStatus, sort, order));
    }

    /**
     * 获取留言分类统计信息
     *
     * @return map集合 key:message_type、value:条数
     */
    @Override
    public List<Map<Object, Object>> getStaticInfo() {

        //查询统计信息
        List<Map<Object, Object>> staticInfo = leaveMessageMapper.getMessageTypeStaticInfo();

        //将map中的key:message_type的value从数字转换为字符串，易于理解
        for (Map<Object, Object> map : staticInfo) {

            //获取类型数字
            Integer type = (Integer)map.get("message_type");

            //将每一个数字值转换为对应的字符串值
            for (MessageType enumType : MessageType.values()) {

                //将每一个数字值转换为对应的字符串值
               if(Objects.equals(enumType.getValue(), type)){

                   //将字符串值设置为value
                   map.put("message_type",enumType.getName());
               }
            }
        }
        return staticInfo;
    }


}
