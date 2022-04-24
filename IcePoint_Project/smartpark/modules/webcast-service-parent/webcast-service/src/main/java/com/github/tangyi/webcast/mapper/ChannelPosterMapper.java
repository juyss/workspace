package com.github.tangyi.webcast.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.webcast.api.model.ChannelDept;
import com.github.tangyi.webcast.api.model.ChannelPoster;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChannelPosterMapper extends CrudMapper<ChannelPoster> {

}
