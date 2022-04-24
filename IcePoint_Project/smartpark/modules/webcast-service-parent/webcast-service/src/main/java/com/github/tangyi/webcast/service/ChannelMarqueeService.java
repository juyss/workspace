package com.github.tangyi.webcast.service;

import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.webcast.api.model.ChannelDept;
import com.github.tangyi.webcast.api.model.ChannelMarquee;
import com.github.tangyi.webcast.mapper.ChannelDeptMapper;
import com.github.tangyi.webcast.mapper.ChannelMarqueeMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class ChannelMarqueeService extends CrudService<ChannelMarqueeMapper, ChannelMarquee> {

}
