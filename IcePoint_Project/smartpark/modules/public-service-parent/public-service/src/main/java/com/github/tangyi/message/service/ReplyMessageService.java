package com.github.tangyi.message.service;

import com.github.tangyi.message.entity.ReplyMessage;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: ReplyMessageService
 * @Desc: 留言回复层接口
 * @package com.github.tangyi.message.service
 * @project park
 * @date 2021/3/27 14:08
 */
public interface ReplyMessageService {

    /**
     * 插入一条回复数据
     *
     * @param replyMessage 回复包装对象
     * @return 插入成功的条数
     */
    int insertReply(ReplyMessage replyMessage);

    /**
     * 根据主键ID删除回复信息
     *
     * @param id 主键
     * @return 删除成功的条数
     */
    int deleteById(Long id);

    /**
     * 根据留言ID删除一条回复
     *
     * @param messageIds 留言id
     * @return 删除成功的条数
     */
    int deleteByMessageId(List<Long> messageIds);

    /**
     * 根据ID（主键）查询回复数据
     *
     * @param id id主键
     * @return 回复信息
     */
    ReplyMessage getReplyById(Long id);

    /**
     * 根据留言ID查询回复数据
     *
     * @param messageId 留言ID
     * @return 回复信息
     */
    List<ReplyMessage> getReplyByMessageId(Long messageId);
}
