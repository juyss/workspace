package com.icepoint.base.web.resource.service.complex;

import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.api.entity.LinkTab;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.entity.MetaTab;
import com.icepoint.base.api.entity.Resource;
import com.icepoint.base.web.resource.service.simple.LinkTabService;
import com.icepoint.base.web.resource.service.simple.MetaFldService;
import com.icepoint.base.web.resource.service.simple.MetaTabService;
import com.icepoint.base.web.resource.service.simple.ResourceService;
import com.icepoint.base.web.resource.util.ResourceUtils;
import com.icepoint.base.web.sys.entity.Dict;
import com.icepoint.base.web.sys.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@Cacheable(cacheNames = "BaseService.RESOURCE_METADATA")
@Service
@RequiredArgsConstructor
public class ResourceMetadataServiceImpl implements ResourceMetadataService {

    private final ResourceService resourceService;
    private final MetaTabService metaTabService;
    private final MetaFldService metaFldService;
    private final LinkTabService linkTabService;
    private final DictService dictService;

    @Nullable
    @Override
    public ResourceMetadata get(Long resId) {
        //获取资源描述
        Resource resource = resourceService.get(resId);
        if (resource == null)
            return null;

        return get(resource);
    }

    @Nullable
    @Override
    public ResourceMetadata get(String key) {
        String resourceName = ResourceUtils.getResourceName(key);
        Assert.hasText(resourceName, "找不到key为[" + key + "]的resource name");

        Resource query = Resource.builder().resCode(resourceName).deleted(0).build();
        Resource resource = resourceService.get(Example.of(query));
        if (resource == null)
            return null;

        return get(resource);
    }

    @Nullable
    @Override
    public ResourceMetadata get(Resource resource) {
        //查询资源对应表元数据
        MetaTab metaTab = metaTabService.get(Example.of(MetaTab.builder()
                .resId(resource.getId())
                .deleted(0)
                .build())
        );
        Assert.notNull(metaTab, "表元数据不存在");

        //查询对应字段元数据
        MetaField metaField = new MetaField();
        metaField.setTabId(metaTab.getId());
        metaField.setOwnerId(resource.getOwnerId());
        metaField.setAppId(resource.getAppId());
        metaField.setDeleted(0);
        List<MetaField> metaFields = metaFldService.list(Example.of(metaField));
        Assert.notEmpty(metaFields, "字段元数据不存在");

        // 查询对应字典
        String resourceName = resource.getResCode();
        Dict queryDict = Dict.builder()
                .categoryEn(ResourceUtils.getDictionaryCategory())
                .itemEn(resourceName)
                .ownerId(resource.getOwnerId())
                .appId(resource.getAppId())
                .deleted(0)
                .build();
        Dict dict = dictService.get(Example.of(queryDict));
        Assert.notNull(dict, "资源字典不存在");

        // 查询关联元数据
        LinkTab linkTab = new LinkTab();
        linkTab.setTabId(metaTab.getId());
        List<LinkTab> linkTabs = linkTabService.list(Example.of(linkTab));

        List<LinkTab> linkFields;
        List<LinkTab> virtualFields;
        if (!CollectionUtils.isEmpty(linkTabs)) {
            int linkTypeFld = 2;// 暂且写死 FIXME

            if (Runtime.getRuntime().availableProcessors() <= 1) {
                linkFields = new ArrayList<>();
                for (LinkTab tab : linkTabs) {
                    linkTab = tab;
                    if (linkTypeFld == (linkTab.getLinkType())) {
                        linkFields.add(linkTab);
                    }
                }
            } else {
                linkFields = linkTabs.parallelStream().filter(item -> linkTypeFld == (item.getLinkType())).collect(Collectors.toList());
            }

            int virtualTypeFld = 3;// 暂且写死 FIXME

            if (Runtime.getRuntime().availableProcessors() <= 1) {
                virtualFields = new ArrayList<>();
                for (LinkTab tab : linkTabs) {
                    linkTab = tab;
                    if (virtualTypeFld == linkTab.getLinkType()) {
                        virtualFields.add(linkTab);
                    }
                }
            } else {
                virtualFields = linkTabs.parallelStream().filter(item -> virtualTypeFld == item.getLinkType()).collect(Collectors.toList());
            }

        } else {
            linkFields = Collections.emptyList();
            virtualFields = Collections.emptyList();
        }

        String resourceKey = ResourceUtils.getResourceKey(resourceName.trim());
        if (!StringUtils.hasText(resourceKey)) {
            throw new IllegalStateException("资源名称对应的key不存在: " + resourceKey);
        }
        return new ResourceMetadata(metaTab, metaFields, linkFields, virtualFields, resource, dict, resourceKey, resourceName);
    }
}
