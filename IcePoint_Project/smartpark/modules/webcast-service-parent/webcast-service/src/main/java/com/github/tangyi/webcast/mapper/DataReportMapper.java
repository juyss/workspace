package com.github.tangyi.webcast.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.webcast.api.model.ChannelPoster;
import com.github.tangyi.webcast.api.model.DataReport;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DataReportMapper extends CrudMapper<DataReport> {

}
