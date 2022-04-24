package com.icepoint.base.web.resource.mapper;

import com.icepoint.base.web.resource.component.generic.DatabaseTableParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface DatabaseTableMapper {

	void save(@Param("param") DatabaseTableParam param);
	int update(@Param("param") DatabaseTableParam param);
    int delete(@Param("param") DatabaseTableParam param);
    List<Map<String, Object>> list(@Param("param") DatabaseTableParam param, @Nullable Specification<?> spec);
    Page<Map<String, Object>> page(@Param("param") DatabaseTableParam param, @Nullable Specification<?> spec, Pageable pageable);
    
}
