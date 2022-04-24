package com.github.tangyi.file.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.file.api.model.InstitutionHistory;
import com.github.tangyi.file.api.model.Plan;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlanMapper extends CrudMapper<Plan> {

    List<Plan> listByIdList(List<Long> idList);
}
