package com.icepoint.base.web.resource.mapper;

import com.icepoint.base.api.entity.Approval;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ApprovalMapper extends MybatisMapper<Approval, Long> {

    void add(Approval entity);
}
