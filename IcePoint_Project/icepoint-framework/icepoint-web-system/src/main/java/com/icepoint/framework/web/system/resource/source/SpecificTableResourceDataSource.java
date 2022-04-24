package com.icepoint.framework.web.system.resource.source;

import com.icepoint.framework.core.util.CollectionUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.util.SortOnly;
import com.icepoint.framework.web.system.dao.SpecificTableMapper;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.ResourceModelConverter;
import com.icepoint.framework.web.system.resource.parser.SpecificTableSqlParser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
public class SpecificTableResourceDataSource extends AbstractResourceDataSource {

    private final SpecificTableMapper mapper;

    private final SpecificTableSqlParser sqlParser;

    private final ResourceModelConverter converter;

    @Override
    public String getIdentifier() {
        return "2";
    }

    @Override
    public Optional<ResourceModel> findById(Lookup lookup, Object id) {
        return findOne(lookup, sqlParser.findById(lookup, id));
    }

    @Override
    public Optional<ResourceModel> findOne(Lookup lookup) {
        return findOne(lookup, sqlParser.findOne(lookup));
    }

    @Override
    public List<ResourceModel> findAll(Lookup lookup) {
        return findPage(lookup, Pageable.unpaged()).getContent();
    }

    @Override
    public List<ResourceModel> findAll(Lookup lookup, Sort sort) {
        return findPage(lookup, new SortOnly(sort)).getContent();
    }

    @Override
    public Page<ResourceModel> findAll(Lookup lookup, Pageable pageable) {
        return findPage(lookup, pageable);
    }

    @Override
    public ResourceModel save(ResourceModel model) {
        Long id = model.getId();
        boolean isNew = id == null;

        int result;
        if (isNew) {

            result = mapper.add(sqlParser.add(model));

        } else {

            if (isPatchRequest()) {
                ResourceModel oldModel = findById(model.getLookup(), id)
                        .orElseThrow(() -> new IllegalArgumentException("更新失败, 对应id的数据不存在: " + id));
                combineModel(model, oldModel);
            }

            result = mapper.update(sqlParser.update(model));
        }

        Assert.state(result == 1, "保存数据失败");
        return model;
    }

    @Override
    public void deleteById(Lookup lookup, Object id) {
        int update = mapper.update(sqlParser.deleteById(lookup, id));
        Assert.state(update == 1, "删除数据失败");
    }

    @Override
    public void deleteAllByIds(Lookup lookup, Iterable<Object> ids) {
        int result = mapper.update(sqlParser.deleteAllByIds(lookup, ids));
        long count = Streams.stream(ids).count();
        Assert.state(result == count, "删除数据失败");
    }

    private Optional<ResourceModel> findOne(Lookup lookup, String sql) {
        List<Map<String, Object>> result = mapper.findAll(sql, Pageable.unpaged()).getContent();
        return Optional.ofNullable(CollectionUtils.getNullableSingleElement(result))
                .map(properties -> converter.convert(lookup, properties));
    }

    private Page<ResourceModel> findPage(Lookup lookup, Pageable pageable) {
        return mapper.findAll(sqlParser.findAll(lookup), pageable)
                .map(properties -> converter.convert(lookup, properties));
    }

}
