package com.icepoint.base.web.resource.mapper;

import com.icepoint.base.api.entity.InstitutionHistory;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InstitutionHistoryMapper extends MybatisMapper<InstitutionHistory, Long> {

    void add(InstitutionHistory institutionHistory);

    void deleteByIdList(List<Long> idList);

    List<InstitutionHistory> listInstitutionHistory(List<Long> idList);
}
