package com.icepoint.base.web.resource.mapper;

import com.icepoint.base.api.entity.Head;
import com.icepoint.base.web.basic.repository.MybatisMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface HeadMapper extends MybatisMapper<Head, Long> {

    List<Head> listBy(@Param("docType") Integer docType, @Param("id") Long id);

    List<Head> listByDocNos(@Param("docType") Integer docType, @Param("docNos") Collection<String> docNos);

    Page<String> getDocNoList(@Nullable @Param("queryEntity") Object queryEntity, @Param("docType") Integer docType, Pageable pageable);

    Long nextId(@Param("docType") Integer docType);

    String nextDocNo(@Param("docType") Integer docType, @Param("resourceName") String resourceName);

    int updateAll(@Param("entities") List<Head> entities);

    void update(Head entity);

    void deleteBatch(@Param("id") Long id, @Param("docType") Integer docType);

    void deleteSeq(@Param("id") Long id, @Param("docType") Integer docType);

    void addAllBig(@Param("entities") List<Head> entities);

    List<String> getParkDataYearXAxisList();

    List<Map> getParkDataYearMetadata();

    List<Map> getParkDataYear();

    void updateForBig(Head head);

    List<String> listPlanAnnex(List<String> docNoList);

    List<String> listMgtInstututionAnnex(List<String> docNoList);
}
