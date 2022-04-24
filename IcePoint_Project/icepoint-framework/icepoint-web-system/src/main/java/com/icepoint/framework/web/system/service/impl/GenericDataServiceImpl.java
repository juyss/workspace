package com.icepoint.framework.web.system.service.impl;

import com.icepoint.framework.web.core.support.StdEntityServiceImpl;
import com.icepoint.framework.web.system.dao.GenericDataMapper;
import com.icepoint.framework.web.system.dao.GenericDataRepository;
import com.icepoint.framework.web.system.entity.GenericData;
import com.icepoint.framework.web.system.entity.GenericDataSequence;
import com.icepoint.framework.web.system.entity.QGenericData;
import com.icepoint.framework.web.system.service.GenericDataSequenceService;
import com.icepoint.framework.web.system.service.GenericDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Service
public class GenericDataServiceImpl
        extends StdEntityServiceImpl<GenericData, Long>
        implements GenericDataService {

    private final GenericDataSequenceService sequenceService;

    private final GenericDataRepository repository;

    private final GenericDataMapper mapper;

    @Override
    public List<GenericData> findAllByResourceIdAndObjectId(Long resourceId, Long objectId) {
        GenericDataSequence sequence = findSequenceByResourceIdAndObjectId(resourceId, objectId);
        return findAllByResourceIdAndNos(resourceId, Collections.singletonList(sequence.getNo()));
    }

    @Override
    public List<GenericData> findAllByResourceIdAndNos(Long resourceId, List<String> nos) {
        QGenericData q = QGenericData.genericData;
        return repository.findAll(q.resourceId.eq(resourceId)
                .and(q.no.in(nos)), false);
    }

    @Override
    public GenericData updateByResourceIdAndNoAndName(GenericData genericData) {

        Long resourceId = genericData.getResourceId();
        String no = genericData.getNo();
        String name = genericData.getName();

        GenericData data = repository.findByResourceIdAndNoAndName(resourceId, no, name)
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "无法找到对应通用数据: no: %s, resourceId: %s", no, resourceId)));

        data.setValue(genericData.getValue());
        data.setBigValue(genericData.getBigValue());

        repository.save(data);
        return data;
    }

    @Override
    public void deleteAllByResourceIdAndObjectId(Long resourceId, Long objectId) {
        GenericDataSequence sequence = findSequenceByResourceIdAndObjectId(resourceId, objectId);
        mapper.deleteAllByResourceIdAndNo(resourceId, sequence.getNo());
    }

    private GenericDataSequence findSequenceByResourceIdAndObjectId(Long resourceId, Long objectId) {
        return sequenceService.findByResourceIdAndObjectId(resourceId, objectId)
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "找不到Sequence数据, 资源id: %s, 数据id: %s", resourceId, objectId)));
    }
}
