package com.icepoint.base.web.info.mapper;

import com.icepoint.base.web.basic.repository.MybatisMapper;
import com.icepoint.base.web.info.entity.Banner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * banner记录映射
 *
 */
@Mapper
@Repository
public interface BannerMapper extends MybatisMapper<Banner, Long> {

    int batchUpdate(List<Map<String, Object>> list);

    Integer maxIndex(Banner banner);
}