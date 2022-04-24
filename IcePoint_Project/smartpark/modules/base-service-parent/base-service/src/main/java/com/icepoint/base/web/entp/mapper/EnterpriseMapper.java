package com.icepoint.base.web.entp.mapper;

import com.icepoint.base.api.entity.Enterprise;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EnterpriseMapper extends MybatisMapper<Enterprise, Long> {

    List<Enterprise> listBySql(String sql);

}
