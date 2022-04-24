package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.TPlate;
import org.apache.ibatis.annotations.Mapper;

/**
 * t_plate 板块
 *
 * @author jy
 * @since 2020/11/03
 */
@Mapper
public interface TPlateBaseMapper extends CommonDaoMapper<TPlate> {
}
