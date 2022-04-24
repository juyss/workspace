package com.icepoint.base.web.resource.mapper;

import com.icepoint.base.api.entity.Plan;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PlanMapper extends MybatisMapper<Plan, Long> {

    Page<Plan> pageByHelper(@Param("fileName") String fileName, @Param("intelligence") String intelligence, @Param("deptIdList") List<String> deptIdList, Pageable pageable);

    List<Plan> listByIdList(List<Long> idList);

    void deleteByIdList(List<Long> idList);
}
