package com.github.tangyi.webcast.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.webcast.api.model.ChannelDept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelDeptMapper extends CrudMapper<ChannelDept> {

    List<ChannelDept> listByDeptIdList(List<Long> deptIdList);

    @Select("select dept_id from webcast_channel_dept where channel_id = #{id}")
    ChannelDept queryByChannelId(@Param("id") String channelId);
}
