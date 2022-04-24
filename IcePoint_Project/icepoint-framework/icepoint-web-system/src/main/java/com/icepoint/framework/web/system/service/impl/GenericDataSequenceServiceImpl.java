package com.icepoint.framework.web.system.service.impl;

import com.icepoint.framework.web.core.support.BaseEntityServiceImpl;
import com.icepoint.framework.web.system.dao.GenericDataSequenceMapper;
import com.icepoint.framework.web.system.dao.GenericDataSequenceRepository;
import com.icepoint.framework.web.system.entity.GenericDataSequence;
import com.icepoint.framework.web.system.entity.QGenericDataSequence;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModelMetadata;
import com.icepoint.framework.web.system.resource.parser.QuerySqlParser;
import com.icepoint.framework.web.system.resource.query.Query;
import com.icepoint.framework.web.system.service.GenericDataSequenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
@Service
public class GenericDataSequenceServiceImpl
        extends BaseEntityServiceImpl<GenericDataSequence, Long>
        implements GenericDataSequenceService {

    private final GenericDataSequenceRepository repository;

    private final GenericDataSequenceMapper mapper;

    private final QuerySqlParser querySqlParser;

    @Override
    public Page<String> findNoPage(Lookup lookup, Pageable pageable) {
        ResourceModelMetadata metadata = lookup.getMetadata();
        Query query = lookup.getQuery();
        Sort sort = pageable.getSort();

        // 不排序也没有查询条件的情况，不需要解析复杂的sql，直接按照resourceId分页查询就可以
        if (query.isEmpty() && sort.isUnsorted()) {
            QGenericDataSequence q = QGenericDataSequence.genericDataSequence;
            return repository.findAll(q.resourceId.eq(metadata.getResource().getId())
                    .and(q.deleted.eq(false)), pageable)
                    .map(GenericDataSequence::getNo);
        } else {
            String sql = querySqlParser.parse(lookup, pageable);
            return mapper.findAllBySql(sql, pageable)
                    .map(GenericDataSequence::getNo);
        }
    }

    @Override
    public Long nextId(Long resourceId) {
        return repository.nextId(resourceId);
    }

    @Override
    public String nextNo(Long resourceId, Long assetDefineId) {
        return repository.nextNo(resourceId, assetDefineId);
    }

    @Override
    public Optional<GenericDataSequence> findByResourceIdAndObjectId(Long resourceId, Long objectId) {
        QGenericDataSequence q = QGenericDataSequence.genericDataSequence;
        return repository.findOne(q.resourceId.eq(resourceId)
                .and(q.objectId.eq(objectId))
                .and(q.deleted.eq(false)));
    }

    @Override
    public void deleteAllByResourceIdAndObjectId(Long resourceId, Long objectId) {
        GenericDataSequence sequence = findByResourceIdAndObjectId(resourceId, objectId)
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "该资源不存在对应id的数据, 资源id: %s, 数据id: %s",
                        resourceId, objectId)));
        sequence.setDeleted(true);
        repository.save(sequence);
    }

    @Override
    public void updateNoByResourceIdAndObjectId(String no, Long resourceId, Long objectId) {
        QGenericDataSequence q = QGenericDataSequence.genericDataSequence;
        boolean isPresent = repository.findOne(q.resourceId.eq(resourceId)
                .and(q.objectId.eq(objectId)))
                .isPresent();
        Assert.isTrue(isPresent,"找不到对应的Sequence");
        mapper.updateForNewDataByResourceIdAndObjectId(no, resourceId, objectId);
    }
}
