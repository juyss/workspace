package com.github.tangyi.message.mapper;

import com.github.tangyi.message.entity.AutoReply;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName AutoReplyMapper
 * @description
 * @date 2021-05-19 10:00
 */
@Repository
public interface AutoReplyMapper {

    /**
     * 根据id删除一条数据（逻辑删除）
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id") Long id);

    /**
     * 根据id数组批量删除（逻辑删除）
     * @param ids
     * @return
     */
    int deleteByIds(@Param("ids") List<Long> ids);

    /**
     * 添加一条数据
     * @param record
     * @return
     */
    int insert(AutoReply record);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    AutoReply selectByPrimaryKey(@Param("id") Long id);

    /**
     * 查询列表
     * @param sort
     * @param order
     * @return
     */
    List<AutoReply> selectAll(@Param("name") String name, @Param("sort") String sort, @Param("order") String order);

    /**
     * 根据id更新一条数据
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AutoReply record);
}
