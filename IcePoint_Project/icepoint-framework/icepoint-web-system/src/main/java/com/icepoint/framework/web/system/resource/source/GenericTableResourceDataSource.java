package com.icepoint.framework.web.system.resource.source;

import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.CollectionUtils;
import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.core.util.Streams;
import com.icepoint.framework.data.util.DirectionComparator;
import com.icepoint.framework.data.util.SortOnly;
import com.icepoint.framework.web.system.entity.GenericData;
import com.icepoint.framework.web.system.entity.GenericDataSequence;
import com.icepoint.framework.web.system.entity.Resource;
import com.icepoint.framework.web.system.resource.Lookup;
import com.icepoint.framework.web.system.resource.ResourceModel;
import com.icepoint.framework.web.system.resource.ResourceModelMetadata;
import com.icepoint.framework.web.system.resource.query.GenericTableResourceModelConverter;
import com.icepoint.framework.web.system.service.GenericDataSequenceService;
import com.icepoint.framework.web.system.service.GenericDataService;
import com.icepoint.framework.web.system.util.SystemResourceUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.Assert;

import java.util.*;

/**
 * @author Jiawei Zhao
 */
@RequiredArgsConstructor
public class GenericTableResourceDataSource extends AbstractResourceDataSource {

    private final GenericTableResourceModelConverter resourceModelConverter;

    private final GenericDataService genericDataService;

    private final GenericDataSequenceService genericDataSequenceService;

    @Override
    public String getIdentifier() {
        return "1";
    }

    @Override
    public Optional<ResourceModel> findById(Lookup lookup, Object id) {

        long longId;
        try {
            longId = SimpleTypeUtils.parseLong(id);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("通用表的id类型只支持长整型");
        }

        Resource resource = lookup.getMetadata().getResource();
        return Optional.of(genericDataService.findAllByResourceIdAndObjectId(resource.getId(), longId))
                .filter(list -> !list.isEmpty())
                .map(list -> resourceModelConverter.convert(lookup, list))
                .map(CollectionUtils::getRequiredSingleElement);
    }

    @Override
    public Optional<ResourceModel> findOne(Lookup lookup) {
        List<ResourceModel> models = findAll(lookup);
        if (models.size() > 1) {
            throw new IncorrectResultSizeDataAccessException(1, models.size());
        }
        return Optional.of(models.iterator())
                .filter(Iterator::hasNext)
                .map(Iterator::next);
    }

    /**
     * 会构造一个不含分页参数也不包含排序参数的{@link Pageable}分页对象，并且调用分页查询方法{@link #findAll(Lookup, Pageable)}
     *
     * @param lookup 查询对象
     * @return 查询结果
     */
    @Override
    public List<ResourceModel> findAll(Lookup lookup) {
        return findAll(lookup, Pageable.unpaged()).getContent();
    }

    /**
     * 会构造一个不含分页参数但包含排序参数的{@link Pageable}分页对象，并且调用分页查询方法{@link #findAll(Lookup, Pageable)}
     *
     * @param lookup 查询对象
     * @return 查询结果
     */
    @Override
    public List<ResourceModel> findAll(Lookup lookup, Sort sort) {
        return findAll(lookup, new SortOnly(sort)).getContent();
    }

    @Override
    public Page<ResourceModel> findAll(Lookup lookup, Pageable pageable) {

        ResourceModelMetadata metadata = lookup.getMetadata();
        Resource resource = metadata.getResource();
        Long resourceId = resource.getId();

        Sort sort = pageable.getSort();

        // 先用分页的查询方式，把要查询的通用数据的no查出来
        Page<String> noPage = genericDataSequenceService.findNoPage(lookup, pageable);
        if (noPage.isEmpty()) {
            return Page.empty(pageable);
        }

        // 这里查出来的数据会丢失排序
        List<GenericData> dataList = genericDataService.findAllByResourceIdAndNos(resourceId, noPage.getContent());
        List<ResourceModel> models = resourceModelConverter.convert(lookup, dataList);

        // 如果有必要的话，根据字段进行排序操作
        if (sort.isSorted() && !CollectionUtils.isEmpty(models)) {
            List<Sort.Order> orderList = sort.toList();

            // 后面排序的顺序会覆盖前面的，先排后面的排序字段
            for (int i = orderList.size() - 1; i >= 0; i--) {
                Sort.Order order = orderList.get(i);
                String property = order.getProperty();
                Sort.Direction direction = order.getDirection();
                models.sort(Comparator.comparing(
                        model -> CastUtils.cast(model.getProperty(property)),
                        new DirectionComparator(direction)));
            }
        }

        return CollectionUtils.isEmpty(models)
                ? Page.empty(pageable)
                : PageableExecutionUtils.getPage(models, pageable, noPage::getTotalElements);
    }

    @Override
    public ResourceModel save(ResourceModel model) {
        // 根据id决定是新增还是更新
        return model.getId() == null ? saveNew(model) : saveOld(model);
    }

    @Override
    public List<ResourceModel> saveAll(Iterable<ResourceModel> models) {
        return Streams.streamable(models)
                .map(this::save)
                .toList();
    }

    @Override
    public void deleteById(Lookup lookup, Object id) {
        Long resourceId = lookup.getMetadata().getResource().getId();
        long objectId = SimpleTypeUtils.parseLong(id);

        genericDataService.deleteAllByResourceIdAndObjectId(resourceId, objectId);
        genericDataSequenceService.deleteAllByResourceIdAndObjectId(resourceId, objectId);
    }

    @Override
    public void deleteAllByIds(Lookup lookup, Iterable<Object> ids) {
        ids.forEach(id -> deleteById(lookup, id));
    }

    private ResourceModel saveNew(ResourceModel model) {

        SystemResourceUtils.validateRequiredProperties(model, true);

        ResourceModelMetadata metadata = model.getLookup().getMetadata();
        Resource resource = metadata.getResource();

        Long resourceId = resource.getId();

        // 生成一个id
        Long newId = genericDataSequenceService.nextId(resourceId);
        // 生成一个docNo
        String no = genericDataSequenceService.nextNo(resourceId, metadata.getAssetDefine().getId());

        // 向新增的实体设置id和docNo
        model.setProperty("id", newId);
        model.setProperty("no", no);

        // 准备一些共用属性
        long now = System.currentTimeMillis();
        Long appId = null;
        Long ownerId = null;
        boolean deleted = false;

        // 将map转换成Head的列表（小字段）
        List<GenericData> dataList = Streams.streamable(metadata.getFields())
                .filter(fieldMetadata -> {
                    String fieldName = SystemResourceUtils.getFieldName(fieldMetadata);
                    return model.hasProperty(fieldName) && Objects.nonNull(model.getProperty(fieldName));
                })
                .map(fieldMetadata -> {

                    String fieldName = SystemResourceUtils.getFieldName(fieldMetadata);
                    String value = model.getProperty(fieldName, String.class);

                    boolean big = SystemResourceUtils.isBigValueField(fieldMetadata);

                    GenericData data = GenericData.builder()
                            .appId(appId)
                            .ownerId(ownerId)
                            .deleted(deleted)
                            .createTime(now)
                            .resourceId(resourceId)
                            .no(no)
                            .name(fieldName)
                            .value(value)
                            .big(big)
                            .build();

                    if (big) {
                        data.setBigValue(value);
                    } else {
                        data.setValue(value);
                    }

                    return data;
                })
                .toList();

        // 批量新增所有属性
        dataList = genericDataService.saveAll(dataList);

        List<ResourceModel> models = resourceModelConverter.convert(model.getLookup(), dataList);
        ResourceModel result = CollectionUtils.getRequiredSingleElement(models);

        // 更新Sequence表
        genericDataSequenceService.updateNoByResourceIdAndObjectId(no, resourceId, newId);
        return result;
    }

    private ResourceModel saveOld(ResourceModel model) {

        Long modelId = model.getId();
        Assert.notNull(modelId, "更新数据的id不能为空");

        // 如果是局部更新，从数据库查询数据并且合并
        if (isPatchRequest()) {
            ResourceModel oldModel = findById(model.getLookup(), modelId)
                    .orElseThrow(() -> new IllegalArgumentException("对应id的数据不存在: " + modelId));
            combineModel(model, oldModel);
        }

        SystemResourceUtils.validateRequiredProperties(model, false);

        ResourceModelMetadata metadata = model.getLookup().getMetadata();
        Lookup lookup = model.getLookup();
        Long resourceId = metadata.getResource().getId();

        GenericDataSequence sequence = genericDataSequenceService.findByResourceIdAndObjectId(resourceId, modelId)
                .orElseThrow(() -> new IllegalArgumentException("该id的数据不存在: " + modelId));

        String no = sequence.getNo();

        // 将map转换成Head的列表
        List<GenericData> dataList = Streams.streamable(metadata.getFields())
                .map(fieldMetadata -> {

                    String name = SystemResourceUtils.getFieldName(fieldMetadata);
                    String value = model.getProperty(name, String.class);

                    GenericData data = GenericData.builder()
                            .resourceId(resourceId)
                            .no(no)
                            .name(name)
                            .build();

                    if (SystemResourceUtils.isBigValueField(fieldMetadata)) {
                        data.setBigValue(value);
                    } else {
                        data.setValue(value);
                    }

                    return data;
                })
                .map(genericDataService::updateByResourceIdAndNoAndName)
                .toList();

        return CollectionUtils.getRequiredSingleElement(resourceModelConverter.convert(lookup, dataList));
    }

}
