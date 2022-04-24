package com.icepoint.base.web.resource.service.complex.upper;

import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.vo.LastAndNest;
import com.icepoint.base.web.resource.component.query.QueryParameter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface GenericEntityService {

    GenericEntity get(QueryParameter queryParameter, String key, Object id, SerializeType serializeType);

    List<GenericEntity> list(QueryParameter queryParameter, String key, SerializeType serializeType);

    Page<GenericEntity> page(QueryParameter queryParameter, String key, SerializeType serializeType, Pageable pageable);

    @Transactional
    GenericEntity add(String key, Map<String, Object> entity);

    @Transactional
    int update(String key, Map<String, Object> entity);

    @Transactional
    int delete(String key, List<Object> idList);

    @Transactional
    int approval(String key, Map<String, Object> entity);

    void download(HttpServletRequest request, HttpServletResponse response, String key, List<String> docNoList);

    void excel(String key, MultipartFile excel);

    List<GenericEntity> geom(QueryParameter queryParameter, String key, SerializeType serializeType, Integer geomType);

    LastAndNest lastAndNest(String id, QueryParameter queryParameter, String key, SerializeType serializeType, Pageable pageable);

    int push(String key, List<Map<String, Object>> entityList);
}
