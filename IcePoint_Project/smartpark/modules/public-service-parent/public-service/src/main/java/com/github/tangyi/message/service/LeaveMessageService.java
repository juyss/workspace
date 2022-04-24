package com.github.tangyi.message.service;

import com.github.tangyi.core.common.web.PageResult;
import com.github.tangyi.message.entity.LeaveMessage;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LeaveMessageService
 * @Desc: 留言Service接口
 * @package com.github.tangyi.message.service
 * @project park
 * @date 2021/3/27 14:07
 */
public interface LeaveMessageService {

    /**
     * 添加一条留言
     *
     * @param message 封装的留言对象
     * @return 添加成功的数量
     */
    int insert(LeaveMessage message);

    /**
     * 根据ID删除留言
     * @param ids 留言主键ID集合
     * @return 删除成功的条数
     */
    int deleteById(List<Long> ids);

    /**
     * 根据ID（主键）更新留言审核状态
     *
     * @param id          id主键
     * @param checkStatus 审核状态
     * @return 修改成功的条数
     */
    int updateCheckStatus(Long id, Integer checkStatus);

    /**
     * 根据ID（主键）更新留言回复状态
     *
     * @param id          主键
     * @param replyStatus 回复状态
     * @return 更新成功的条数
     */
    int updateReplyStatus(Long id, Integer replyStatus);

    /**
     * 根据ID（主键）获取留言信息
     *
     * @param id id主键
     * @return 符合条件的留言
     */
    LeaveMessage getMessageById(Long id);

    /**
     * 根据条件查询并排序
     *
     * @param pageNum     页码
     * @param pageSize    页面大小
     * @param type        留言类型
     * @param createDate  创建时间
     * @param checkStatus 审核状态
     * @param replyStatus 回复状态
     * @param sort        排序分类
     * @param order       排序方向
     * @return 留言集合
     */
    PageResult getListByCondition(Integer pageNum,
                                  Integer pageSize,
                                  Integer type,
                                  Date createDate,
                                  Integer checkStatus,
                                  Integer replyStatus,
                                  String sort,
                                  String order);

    /**
     * 获取留言分类统计信息
     * @return map集合 key:message_type、value:条数
     */
    List<Map<Object, Object>> getStaticInfo();
}
