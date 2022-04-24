package com.icepoint.base.web.resource.mapper;

import com.icepoint.base.api.entity.TabSeq;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TabSeqMapper {

    void add(TabSeq tabSeq);

    TabSeq get(@Param("id") Long id, @Param("docType") Integer docType);
}
