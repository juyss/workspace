package com.github.tangyi.user.mapper;

import com.github.tangyi.common.core.model.req.BaseReq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PageClickLogMapper {

    List<Map<String,Object>> statistics(Map<String,Object> param);

    List<Map<String,Object>> getList(BaseReq baseReq, @Param("sort") String sort, @Param("order") String order);
}
