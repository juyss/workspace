package com.github.tangyi.webcast.service;

import com.github.tangyi.webcast.api.model.ChannelSessionInfo;
import com.github.tangyi.webcast.mapper.ChannelSessionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class ChannelSessionService {

    @Autowired
    private ChannelSessionMapper channelSessionMapper;

    public List<ChannelSessionInfo> queryAll() { return this.channelSessionMapper.queryAll();}

    public int insert(ChannelSessionInfo channelSessionInfo) { return this.channelSessionMapper.insert(channelSessionInfo); }


    public List<ChannelSessionInfo> queryByTime() { return this.channelSessionMapper.queryByTime();}


    public int updateStatus(ChannelSessionInfo channelSessionInfo) { return this.channelSessionMapper.updateStatus(channelSessionInfo);}
}
