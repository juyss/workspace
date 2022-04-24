package com.github.tangyi.webcast.mapper;

import com.github.tangyi.webcast.api.model.ChannelSessionInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ChannelSessionMapper {
    @Select("select id,channelId,sessionId,startTime,endTime,upload_status as uploadStatus  from channel_session_info where sessionId = #{sessionId}")
    ChannelSessionInfo selectBySessionId(@Param("sessionId") String sessionId);

    @Select("select id,channelId,sessionId,startTime,endTime,upload_status as uploadStatus from channel_session_info")
    List<ChannelSessionInfo> queryAll();

    @Insert("insert into channel_session_info (id,channelId,sessionId,startTime,endTime,upload_status ) values (null,#{info.channelId},#{info.sessionId},#{info.startTime},#{info.endTime},#{info.uploadStatus})  ")
    int insert(@Param("info") ChannelSessionInfo channelSessionInfo);


    List<ChannelSessionInfo> queryByTime();

    @Update("update channel_session_info set upload_status = #{info.uploadStatus} where sessionId=#{info.sessionId}")
    int updateStatus(@Param("info") ChannelSessionInfo channelSessionInfo);
}
