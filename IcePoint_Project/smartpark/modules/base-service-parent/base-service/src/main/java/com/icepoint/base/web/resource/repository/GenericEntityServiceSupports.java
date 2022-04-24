package com.icepoint.base.web.resource.repository;

import com.github.tangyi.common.basic.vo.UserVo;
import com.github.tangyi.common.core.exceptions.ServiceException;
import com.github.tangyi.common.core.persistence.TreeEntity;
import com.github.tangyi.user.api.dto.DeptDto;
import com.icepoint.base.api.domain.EmptyStringProperty;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.ListJoinEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.entity.MetaTab;
import com.icepoint.base.api.util.ServiceOperation;
import com.icepoint.base.api.util.StreamUtils;
import com.icepoint.base.util.DeptUtils;
import com.icepoint.base.util.UserUtils;
import com.icepoint.base.web.basic.service.ComplexServiceSupports;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.metadata.ResourceType;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.service.complex.ResourceMetadataService;
import com.icepoint.base.web.resource.util.GenericUtils;
import com.icepoint.base.web.resource.util.QueryParameterUtils;
import com.icepoint.base.web.sys.entity.Dict;
import com.icepoint.base.web.sys.service.DictService;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Jiawei Zhao
 */
public interface GenericEntityServiceSupports extends ComplexServiceSupports {


    default void queryAndSetAssociationEntities(
            ResourceMetadata metadata, SerializeType serializeType,
            GenericEntity entity, QueryParameter queryParameter) {

        Require require = queryParameter.getParameter(Require.class);
        List<String> keys;
        if (require != null && !CollectionUtils.isEmpty(keys = require.getKeys())) {
            ResourceMetadataService resourceMetadataService = getRequiredBean(ResourceMetadataService.class);
            List<ResourceMetadata> linkResMetaList = StreamUtils.parallelStreamIfAvailable(keys)
                    .map(resourceMetadataService::get)
                    .collect(Collectors.toList());

            GenericRepositoryAdapter adapter = getRequiredBean(GenericRepositoryAdapter.class);
            Stream.concat(
                    StreamUtils.parallelStreamIfAvailable(metadata.getLinkFields()),
                    StreamUtils.parallelStreamIfAvailable(metadata.getVirtualFields())
            ).forEach(linkTab -> linkResMetaList.stream()
                    .filter(resMeta -> resMeta.getMetaTab().getId().equals(linkTab.getLinkTabId()))
                    .findAny()
                    .ifPresent(resMeta -> {
                        QueryParameter param = QueryParameterUtils.createFrom(linkTab.getLinkInfo(), entity);
                        List<GenericEntity> joinEntities = adapter
                                .get(ResourceType.valueOf(resMeta.getResource().getResType()))
                                .list(param, resMeta, serializeType);

                        if (CollectionUtils.isEmpty(joinEntities)) {
                            linkResMetaList.remove(resMeta);
                            return;
                        }

                        Boolean isList = parsePrimitive(linkTab.getList(), Boolean.class);
                        if (Boolean.FALSE.equals(isList)) {
                            if (joinEntities.size() > 1)
                                throw new IncorrectResultSizeDataAccessException(1, joinEntities.size());

                            entity.setProperty(joinEntities.get(0));
                        } else {
                            entity.setProperty(
                                    new ListJoinEntity(resMeta.getKey(), resMeta.getMetaTab(), joinEntities));
                        }
                        linkResMetaList.remove(resMeta);
                    })
            );

            if (linkResMetaList.size() != 0) {
                String unknownResourceKeys =
                        linkResMetaList.stream().map(ResourceMetadata::getKey).collect(Collectors.joining(", "));
                throw new IllegalArgumentException("Unknown resource keys: [" + unknownResourceKeys + "]");
            }
        }
    }

    default void assertLegalParameter(Map<String, Object> entity, ResourceMetadata metadata, ServiceOperation so) {
        switch (so) {
            case ADD:
                StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                        .filter(metaField -> 1 != metaField.getOptional() && 1 != metaField.getPrimaryKey()
                                && !entity.containsKey(metaField.getNameEn()))
                        .findAny()
                        .ifPresent(metaField -> {
                            throw new IllegalArgumentException("必填字段" + metaField.getNameEn() + "缺少值");
                        });
                break;
            case UPDATE:
                boolean hasPrimaryKey = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                        .anyMatch(metaField -> 1 == metaField.getPrimaryKey()
                                && StringUtils.hasText(Objects.toString(entity.get(metaField.getNameEn()), "")));
                Assert.isTrue(hasPrimaryKey, "缺少主键");

                StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                        .filter(metaField -> 1 != metaField.getOptional() && !entity.containsKey(metaField.getNameEn()))
                        .findAny()
                        .ifPresent(metaField -> {
                            throw new IllegalArgumentException("必填字段" + metaField.getNameEn() + "缺少值");
                        });
                break;
            default:
                throw new IllegalStateException("不支持的参数验证操作: " + so);
        }

    }

    default void addMissingFieldsToEmptyString(ResourceMetadata metadata, GenericEntity entity, ServiceOperation so) {
        List<String> listFieldNames;

        switch (so) {
            case GET:
                listFieldNames = metadata.getMetaFields().stream()
                        .map(MetaField::getNameEn)
                        .collect(Collectors.toCollection(LinkedList::new));
                break;
            case LIST:
            case PAGE:
                listFieldNames = metadata.getMetaFields().stream()
                        .filter(metaField -> metaField.getListField() == 1)
                        .map(MetaField::getNameEn)
                        .collect(Collectors.toCollection(LinkedList::new));
                break;
            default:
                throw new IllegalStateException("结果处理失败: " + so);
        }

        if (listFieldNames.size() > 0) {
            entity.getProperties().forEach(property -> listFieldNames.remove(property.getName()));
            listFieldNames.forEach(fieldName -> {
                if (entity.getPropertyValue(fieldName) == null) {
                    entity.setProperty(new EmptyStringProperty(fieldName));
                }
            });
        }
    }

    default void setCommonValuesIfAbsent(Map<String, Object> entity, ResourceMetadata metadata, ServiceOperation so) {
        // 准备一些共用属性
        MetaTab metaTab = metadata.getMetaTab();
        Long now = Long.valueOf(DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(LocalDateTime.now()));
        Long appId = metaTab.getAppId();
        Long ownerId = metaTab.getOwnerId();
        int deleted = 0;

        UserVo user = UserUtils.getUser();
        Long userId = user == null ? 0L : user.getUserId();

/*        if ("plan".equals(metadata.getKey())) {
            if (user.getDeptId() == null) {
                throw new ServiceException("当前用户无部门，不能新增规划管理数据");
            }
//            entity.put("deptId", user.getDeptId());
        }*/

        switch (so) {
            case ADD:
                entity.putIfAbsent("appId", appId);
                entity.putIfAbsent("ownerId", ownerId);
                entity.put("createTime", now);
                entity.putIfAbsent("createUser", userId);
                entity.put("updateTime", now);
                entity.putIfAbsent("updateUser", userId);
                entity.putIfAbsent("deleted", deleted);
                break;
            case UPDATE:
                entity.put("updateTime", now);
                entity.putIfAbsent("updateUser", userId);
                break;
            default:
                throw new UnsupportedOperationException(so.toString());
        }

    }

    default void dictionaryization(GenericEntity entity, ResourceMetadata metadata, SerializeType serializeType) {
        List<MetaField> dictFields = metadata.getMetaFields().stream()
                .filter(metaField -> metaField.getDictField().equals(1))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dictFields)) {
            return;
        }
        DictService dictService = getRequiredBean(DictService.class);
        List<Dict> dictList = dictService.list();

        dictFields.forEach(metaField -> {
            Object propertyValue = entity.getPropertyValue(metaField.getNameEn());
            if (propertyValue != null) {
                // 字典化
                dictList.stream()
                        .filter(dict -> dict.getCategoryEn().equals(metaField.getCategoryEn())
                                && dict.getCval().equals(propertyValue.toString()))
                        .findAny()
                        .map(Dict::getItem)
                        .ifPresent(item ->
                                entity.setProperty(GenericUtils.newProperty(metaField, item, serializeType))
                        );
            }
        });
    }

    default void dictionaryization(List<GenericEntity> entities, ResourceMetadata metadata, SerializeType serializeType) {
        List<MetaField> dictFields = metadata.getMetaFields().stream()
                .filter(metaField -> metaField.getDictField().equals(1))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dictFields)) {
            return;
        }
        DictService dictService = getRequiredBean(DictService.class);
        List<Dict> dictList = dictService.list();

        dictFields.forEach(metaField ->
                entities.forEach(entity -> {
                    Object propertyValue = entity.getPropertyValue(metaField.getNameEn());
                    if (propertyValue != null) {
                        // 字典化
                        dictList.stream()
                                .filter(dict -> dict.getCategoryEn().equals(metaField.getCategoryEn())
                                        && dict.getCval().equals(propertyValue.toString()))
                                .findAny()
                                .map(Dict::getItem)
                                .ifPresent(item ->
                                        entity.setProperty(GenericUtils.newProperty(metaField, item, serializeType))
                                );
                    }
                })
        );
    }

    default void planQueryParameter(QueryParameter queryParameter, ResourceMetadata metadata) {
        if ("plan".equals(metadata.getKey())) {
            Long deptId = -1L;
            Collection<Object> deptIds = ((GenericQueryParameter) queryParameter).getMatch().getFieldOps().get("deptId").getOps().values();
            for (Object e : deptIds) {
                deptId = new Long(e.toString());
            }
            List<DeptDto> depts = DeptUtils.getDepts();

            List<String> deptIdList = new LinkedList<>();

            for (DeptDto deptDto : depts) {
                if (deptDto.getId() == deptId) {
                    deptIdList = getDeptId(deptDto);
                } else {
                    if (!CollectionUtils.isEmpty(deptDto.getChildren())) {
                        hanoi(deptDto, deptId);
                    }
                }
            }


            Map<Operation, Object> ops = new LinkedHashMap<>();
            ops.put(Operation.IN, String.join(",", deptIdList));
            FieldOperation fieldOperation = new FieldOperation("deptId", ops);
            ((GenericQueryParameter) queryParameter).getMatch().getFieldOps().put("deptId", fieldOperation);
        }
    }

    default List<String> hanoi(DeptDto deptDto, Long deptId) {
        List<String> deptIdList = new LinkedList<>();
        if (deptDto.getId() == deptId) {
            deptIdList = getDeptId(deptDto);
        } else {
            if (!CollectionUtils.isEmpty(deptDto.getChildren())) {
                return hanoi(deptDto, deptId);
            }
        }
        return deptIdList;
    }

    default List<String> getDeptId(DeptDto deptDto) {
        // 遍历deptDto.getChildren()，将ID加入到deptIdList
        List<String> deptIdList = new LinkedList<>();
        if (!CollectionUtils.isEmpty(deptDto.getChildren())) {
            for (TreeEntity child : deptDto.getChildren()) {
                deptIdList.add(child.getId().toString());
            }
        }
        return deptIdList;
    }

}
