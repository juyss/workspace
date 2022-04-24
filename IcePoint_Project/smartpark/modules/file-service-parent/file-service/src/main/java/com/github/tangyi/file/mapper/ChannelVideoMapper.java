package com.github.tangyi.file.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.webcast.api.model.ChannelVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelVideoMapper extends CrudMapper<ChannelVideo> {

    int insertAll(List<ChannelVideo> channelVideosToUpdate);
    @Select("select * from webcast_channel_video where channel_session_id = #{info.channelSessionId} and obs_uploaded = 'Y' ")
    List<ChannelVideo> getstatus(@Param("info") ChannelVideo channelVideo);
}
