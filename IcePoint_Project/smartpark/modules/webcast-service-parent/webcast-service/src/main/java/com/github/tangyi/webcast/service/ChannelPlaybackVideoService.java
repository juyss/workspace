package com.github.tangyi.webcast.service;

import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.webcast.api.model.ChannelVideo;
import com.github.tangyi.webcast.mapper.ChannelVideoMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ChannelPlaybackVideoService extends CrudService<ChannelVideoMapper, ChannelVideo> {
    @Autowired
    private ChannelVideoMapper channelVideoMapper;

    public int saveAll(List<ChannelVideo> channelVideosToUpdate) {
        return dao.insertAll(channelVideosToUpdate);
    }

    public List<ChannelVideo> queryByChannel( ChannelVideo channelVideo){return this.channelVideoMapper.queryByChannel(channelVideo);}
}
