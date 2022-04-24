package com.github.tangyi.webcast.service;

import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.webcast.api.model.ChannelPoster;
import com.github.tangyi.webcast.mapper.ChannelPosterMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ChannelPosterService extends CrudService<ChannelPosterMapper, ChannelPoster> {

}
