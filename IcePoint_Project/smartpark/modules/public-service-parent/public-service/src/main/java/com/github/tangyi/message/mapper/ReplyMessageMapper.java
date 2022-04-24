package com.github.tangyi.message.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.message.entity.ReplyMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: ReplyMessageMapper
 * @Desc: 留言回复表Mapper
 * @package com.github.tangyi.message.mapper
 * @project park
 * @date 2021/3/27 14:00
 */
@Mapper
@Repository
public interface ReplyMessageMapper extends CrudMapper<ReplyMessage> {

    /**
     * 添加一条回复
     * @param replyMessage 回复封装对象
     * @return 添加成功的条数
     */
    int insertReply(ReplyMessage replyMessage);

    /**
     * 根据主键ID删除一条回复
     * @param id 主键ID
     * @return 删除成功的条数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 根据留言ID删除一条回复
     *
     * @param ids 留言id集合
     * @return 删除成功的条数
     */
    int deleteByMessageId(@Param("ids") List<Long> ids);

    /**
     * 根据主键ID查询回复
     * @param id 主键ID
     * @return 符合条件的回复
     */
    ReplyMessage getReplyById(@Param("id") Long id);

    /**
     * 根据留言ID查询回复
     * @param messageId 留言ID
     * @return 符合条件的回复
     */
    List<ReplyMessage> getReplyByMessageId(@Param("messageId") Long messageId);
}
