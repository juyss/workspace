package com.github.tangyi.user.mapper;

import com.github.tangyi.common.basic.model.Log;
import com.github.tangyi.common.core.persistence.CrudMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 日志
 *
 * @author tangyi
 * @date 2018/10/31 20:38
 */
@Mapper
public interface InformationMapper extends CrudMapper<Log> {
}
