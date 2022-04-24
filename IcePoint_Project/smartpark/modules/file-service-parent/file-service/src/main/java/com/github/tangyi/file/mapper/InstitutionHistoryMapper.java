package com.github.tangyi.file.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.file.api.model.InstitutionHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InstitutionHistoryMapper extends CrudMapper<InstitutionHistory> {

    void add(InstitutionHistory institutionHistory);

    void deleteByIdList(List<Long> idList);

    List<InstitutionHistory> listInstitutionHistory(List<Long> idList);
}
