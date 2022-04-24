package com.github.tangyi.webcast.mapper;
import com.github.tangyi.webcast.api.model.ChannelWhiteList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChannelWhiteListMapper {

     int insertWhiteList(ChannelWhiteList channelWhiteList);


     List<ChannelWhiteList> selectById(@Param("id") Long id);
}
