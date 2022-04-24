package com.github.tangyi.message.service.impl;

import com.github.tangyi.message.entity.ReplyMessage;
import com.github.tangyi.message.mapper.ReplyMessageMapper;
import com.github.tangyi.message.service.ReplyMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: ReplyMessageServiceImpl
 * @Desc: 留言回复Service接口实现类
 * @package com.github.tangyi.message.service.impl
 * @project park
 * @date 2021/3/27 20:05
 */
@Service
public class ReplyMessageServiceImpl implements ReplyMessageService {

    @Autowired
    private ReplyMessageMapper replyMessageMapper;

    /**
     * 插入一条回复数据
     *
     * @param replyMessage 回复包装对象
     * @return 插入成功的条数
     */
    @Override
    public int insertReply(ReplyMessage replyMessage) {
        return replyMessageMapper.insertReply(replyMessage);
    }

    /**
     * 根据主键ID删除回复信息
     *
     * @param id 主键
     * @return 删除成功的条数
     */
    @Override
    public int deleteById(Long id) {
        return replyMessageMapper.deleteById(id);
    }

    /**
     * 根据留言ID删除一条回复
     *
     * @param messageIds 留言id集合
     * @return 删除成功的条数
     */
    @Override
    public int deleteByMessageId(List<Long> messageIds) {
        return replyMessageMapper.deleteByMessageId(messageIds);
    }

    /**
     * 根据ID（主键）查询回复数据
     *
     * @param id id主键
     * @return 回复信息
     */
    @Override
    public ReplyMessage getReplyById(Long id) {
        return replyMessageMapper.getReplyById(id);
    }

    /**
     * 根据留言ID查询回复数据
     *
     * @param messageId 留言ID
     * @return 回复信息
     */
    @Override
    public List<ReplyMessage> getReplyByMessageId(Long messageId) {
        return replyMessageMapper.getReplyByMessageId(messageId);
    }
}
