package com.github.tangyi.webcast.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.webcast.api.model.ChannelVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ChannelVideoMapper extends CrudMapper<ChannelVideo> {

    int insertAll(List<ChannelVideo> channelVideosToUpdate);

    List<ChannelVideo> findListChannelById(List<Long> list);

    ChannelVideo getChannelVideoByid(@Param("id") Long id);

    int save(@Param("info") ChannelVideo channelVideo);

    List<ChannelVideo> queryByChannel(@Param("info") ChannelVideo channelVideo);

    Integer deleteAllChannelId(@Param("channelId") String channelId);

    @Select("select * from webcast_channel_video where file_id = #{fileId}")
    ChannelVideo selectByFileId(@Param("fileId") String fileId);
}
