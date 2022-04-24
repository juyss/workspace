package com.github.tangyi.message.mapper;

import com.github.tangyi.message.entity.LeaveMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author juyss
 * @version 1.0
 * @ClassName: LeaveMessageMapper
 * @Desc: 留言表Mapper
 * @package com.github.tangyi.message
 * @project park
 * @date 2021/3/27 13:57
 */
@Mapper
@Repository
public interface LeaveMessageMapper {

    /**
     * 添加一条留言信息
     *
     * @param message 留言
     * @return 添加成功的条数
     */
    int insert(LeaveMessage message);

    /**
     * 根据ID删除一条留言
     *
     * @param ids 留言主键ID集合
     * @return 删除成功的条数
     */
    int deleteById(@Param("ids") List<Long> ids);

    /**
     * 根据ID更新留言审核状态
     *
     * @param id          留言id
     * @param checkStatus 审核状态：0:不通过、1:通过
     * @return 修改成功的条数
     */
    int updateCheckStatus(@Param("id") Long id, @Param("checkStatus") Integer checkStatus);

    /**
     * 根据ID更新回复状态
     *
     * @param id 留言id
     * @return 修改成功的条数
     */
    int updateReplyStatus(@Param("id") Long id, @Param("replyStatus") Integer replyStatus);

    /**
     * 根据ID获取留言信息
     *
     * @param id id
     * @return 符合条件的留言记录
     */
    LeaveMessage getMessageById(@Param("id") Long id);

    /**
     * 根据条件查询并分页
     *
     * @param type        留言类型
     * @param createDate  创建时间
     * @param checkStatus 审核状态
     * @param replyStatus 恢复状态
     * @param sort        排序字段
     * @param order       排序方向
     * @return
     */
    List<Map<String, Object>> getListByCondition(@Param("type") Integer type,
                                                 @Param("createDate") Date createDate,
                                                 @Param("checkStatus") Integer checkStatus,
                                                 @Param("replyStatus") Integer replyStatus,
                                                 @Param("sort") String sort,
                                                 @Param("order") String order
    );


    /**
     * 获取留言统计信息
     *
     * @return
     */
    List<Map<Object, Object>> getMessageTypeStaticInfo();
}
