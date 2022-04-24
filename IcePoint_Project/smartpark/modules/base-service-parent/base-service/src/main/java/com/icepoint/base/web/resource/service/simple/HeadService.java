package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.api.entity.Head;
import com.icepoint.base.api.vo.ParkData;
import com.icepoint.base.web.basic.service.CrudService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface HeadService extends CrudService<Head, Long> {

    List<Head> listBy(Integer docType, Long id);

    List<Head> listByDocNos(Integer docType, Collection<String> docNos);

    Page<String> getDocNoPage(@Nullable Object queryEntity, Integer docType, Pageable pageable);

    Long getNextId(Integer docType);

    String getNextDocNo(Integer docType, String resourceName);

    @Transactional
    int updateAll(List<Head> headList);

    @Transactional
    List<Head> addAllBig(List<Head> entities);

    @Transactional
    void deleteAll(Long id, Integer docType);

    ParkData getParkDataYear();

    @Transactional
    void updateForBig(Head head);

    List<String> listPlanAnnex(List<String> docNoList);

    List<String> listMgtInstututionAnnex(List<String> docNoList);
}
