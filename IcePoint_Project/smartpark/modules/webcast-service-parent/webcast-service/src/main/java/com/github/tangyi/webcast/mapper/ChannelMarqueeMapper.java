package com.github.tangyi.webcast.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.webcast.api.model.ChannelMarquee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ChannelMarqueeMapper extends CrudMapper<ChannelMarquee> {

}
