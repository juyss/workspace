package com.icepoint.base.util;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.icepoint.base.web.resource.component.metadata.ResourceType;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.entity.MetaTab;
import com.icepoint.base.api.entity.Resource;
import com.icepoint.base.web.resource.service.simple.MetaFldService;
import com.icepoint.base.web.resource.service.simple.MetaTabService;
import com.icepoint.base.web.resource.service.simple.ResourceService;
import com.icepoint.base.web.sys.entity.Dict;
import com.icepoint.base.web.sys.service.DictService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 读取Excel文件的资源元数据配置，加载到数据库中
 *
 * @author Jiawei Zhao
 */
@Deprecated // FIXME: jiawei: 请勿使用这个类，目前蛮多漏洞，即便是正确的文件也无法保证数据的幂等性，待修复
@Lazy
@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS)
public class ResourceMetadataLoader {

    private static final String FILE_PATH;
    private static final String METADATA_CATEGORY = "RESOURCE_ITEM";
    private static final Long APP_ID = 9000000000000005L;
    private static final Long OWNER_ID = 20100000003L;
    private static final Integer DELETED = 0;

    static {
        URL url = ResourceMetadataLoader.class.getClassLoader().getResource("");
        if (url == null)
            throw new IllegalArgumentException();

        FILE_PATH = url.getPath().substring(0, url.getPath().indexOf("/smartpark/"))
                + "/smartpark/06_ProjectDocs/01_ConfigurationItem/05_系统设计/02_接口/接口文档v0.1.xlsx";
    }

    private final DictService dictService;
    private final ResourceService resourceService;
    private final MetaTabService metaTabService;
    private final MetaFldService metaFldService;

    public void loadDicts(int from, int to) {
        readFile(3).subList(from, to).stream()
                .filter(row -> row.size() == 9)
                .forEach(this::toResource);
    }

    private List<List<Object>> readFile(int sheetIndex) {
        ExcelReader reader = ExcelUtil.getReader(FILE_PATH, sheetIndex);
        return reader.read();
    }

    private void toResource(List<Object> row) {
        Dict dict = Dict.builder()
                .category(String.valueOf(row.get(DictColumnIndex.CATEGORY)))
                .categoryEn(String.valueOf(row.get(DictColumnIndex.CATEGORY_EN)))
                .item(String.valueOf(row.get(DictColumnIndex.ITEM)))
                .itemEn(String.valueOf(row.get(DictColumnIndex.ITEM_EN)))
                .cval(String.valueOf(row.get(DictColumnIndex.CVAL)))
                .cvalEn(String.valueOf(row.get(DictColumnIndex.CVAL_EN)))
                .level(Integer.valueOf(String.valueOf(row.get(DictColumnIndex.LEVEL))))
                .deleted(DELETED)
                .ownerId(OWNER_ID)
                .appId(APP_ID)
                .build();

        Dict parentDict = null;
        String parentName = String.valueOf(row.get(DictColumnIndex.PARENT_NAME));
        if (StringUtils.hasText(parentName)) {
            parentDict = dictService.get(Example.of(Dict.builder().item(parentName).categoryEn(METADATA_CATEGORY).build()));
            if (parentDict == null) {
                parentDict = dictService.list(Example.of(Dict.builder().category(parentName).build()))
                        .stream()
                        .filter(p -> !p.getCategoryEn().equals(dict.getCategoryEn()) && dict.getCategoryEn().startsWith("RESOURCE"))
                        .findAny()
                        .orElseThrow(IllegalStateException::new);
                dict.setParentId(parentDict.getId());
            }
        }

        Dict dictDb = dictService.get(Example.of(dict));
        if (dictDb == null) {
            dictService.add(dict);
        } else {
            dict.setId(dictDb.getId());
            dictService.update(dict);
        }

        if (!StringUtils.hasText(dict.getItem()) || !StringUtils.hasText(dict.getItemEn()))
            return;

        Resource resource = Resource.builder()
                .resName(dict.getItem())
                .resCode(dict.getItemEn())
                .ownerId(OWNER_ID)
                .appId(APP_ID)
                .deleted(DELETED)
                .build();

        if (parentDict != null && (StringUtils.hasText(parentDict.getItem()) || StringUtils.hasText(parentDict.getItemEn()))) {
            Resource parentResource = resourceService.get(Example.of(Resource.builder().resCode(parentDict.getItemEn()).build()));
            if (parentResource == null)
                throw new IllegalStateException();
            resource.setParentId(parentResource.getId());
        }

        Resource resourceDb = resourceService.get(Example.of(resource));
        resource.setResIdx(dict.getIdx());
        if (resourceDb == null) {
            resourceService.add(resource);
        } else {
            resource.setId(resourceDb.getId());
            resourceService.update(resource);
        }

    }

    public void loadMetadataField() {
        List<List<Object>> readFile = readFile(5);
        List<List<Object>> subReadFile = subListByCell(readFile, "字段元数据配置", "关联表配置", 2, 0).stream()
                .filter(row -> row.size() == 14)
                .collect(Collectors.toList());

        List<List<Object>> tabIdRows = subReadFile.stream()
                .filter(row -> StringUtils.hasText(String.valueOf(row.get(0))))
                .collect(Collectors.toList());

        Map<String, List<List<Object>>> groupedRows = new HashMap<>();
        for (int i = 0; i < tabIdRows.size(); i++) {
            String tabId = String.valueOf(tabIdRows.get(i).get(0));
            List<List<Object>> fieldRows;
            if (i < tabIdRows.size() - 1) {
                fieldRows = subListByCell(
                        subReadFile,
                        tabId,
                        String.valueOf(tabIdRows.get(i + 1).get(0)),
                        0, 0
                );
            } else if (tabIdRows.size() - i == 1) {
                fieldRows = subListByCell(
                        subReadFile,
                        tabId,
                        null,
                        0, 0
                );
            } else {
                throw new IllegalStateException();
            }

            groupedRows.put(tabId, fieldRows);
        }

        groupedRows.forEach(this::toMetadataFields);

    }

    private void toMetadataFields(String tabId, List<List<Object>> rows) {
        MetaTab metaTab = metaTabService.get(Example.of(MetaTab.builder().name(tabId).build()));
        if (metaTab == null)
            throw new IllegalStateException("找不到表元数据: " + tabId);

        Dict dict = dictService.get(Example.of(Dict.builder().categoryEn(METADATA_CATEGORY).item(tabId).build()));
        if (dict == null)
            throw new IllegalStateException("找不到表元数据对应的字典数据: " + metaTab.getName() + "-" + metaTab.getNameEn());

        rows.forEach(row -> {
            String typeName = String.valueOf(row.get(MetaFieldColumnIndex.TYPE));
            Integer type = null;
            if (StringUtils.hasText(typeName)) {
                Dict fieldTypeDict = dictService.get(Example.of(Dict.builder().cvalEn(typeName).categoryEn("FIELD_ITEM").build()));
                if (fieldTypeDict == null)
                    throw new IllegalStateException("未知数据类型: " + typeName);
                type = Integer.valueOf(fieldTypeDict.getCval());
            }

            MetaField metaField = MetaField.builder()
                    .tabId(metaTab.getId())
                    .appId(APP_ID)
                    .ownerId(OWNER_ID)
                    .deleted(DELETED)
                    .name(String.valueOf(row.get(MetaFieldColumnIndex.NAME)))
                    .nameEn(String.valueOf(row.get(MetaFieldColumnIndex.NAME_EN)))
                    .storeName(String.valueOf(row.get(MetaFieldColumnIndex.STORE_NAME)))
                    .storeNameEn(String.valueOf(row.get(MetaFieldColumnIndex.STORE_NAME_EN)))
                    .type(type)
                    .optional(yesOrNo(String.valueOf(row.get(MetaFieldColumnIndex.OPTIONAL))))
                    .listField(yesOrNo(String.valueOf(row.get(MetaFieldColumnIndex.LIST_FIELD))))
                    .queryField(yesOrNo(String.valueOf(row.get(MetaFieldColumnIndex.QUERY_FIELD))))
                    .dictField(yesOrNo(String.valueOf(row.get(MetaFieldColumnIndex.DICT_FIELD))))
                    .categoryEn(String.valueOf(row.get(MetaFieldColumnIndex.CATEGORY_EN)))
                    .primaryKey(yesOrNo(String.valueOf(row.get(MetaFieldColumnIndex.PRIMARY_KEY))))
                    .uniqueIdx(yesOrNo(String.valueOf(row.get(MetaFieldColumnIndex.UNIQUE_INDEX))))
                    .build();

            MetaField metaFieldDb = metaFldService.get(Example.of(metaField));
            if (metaFieldDb == null) {
                metaFldService.add(metaField);
            } else {
                metaField.setId(metaFieldDb.getId());
                metaFldService.update(metaField);
            }
        });

    }

    private Integer yesOrNo(String text) {
        if (!StringUtils.hasText(text))
            return null;
        text = text.trim();
        if (text.equals("是"))
            return 1;
        else if (text.equals("否"))
            return 0;
        else
            throw new IllegalArgumentException("不支持的值: " + text);
    }

    private List<List<Object>> subListByCell(List<List<Object>> list, String fromCell, String toCell, int fromOffset, int toOffset) {

        List<Object> fromRow = list.stream()
                .filter(row -> row.get(0).equals(fromCell))
                .findAny()
                .orElseThrow(IllegalStateException::new);

        int toIndex;
        if (toCell != null) {
            List<Object> toRow = list.stream()
                    .filter(row -> row.get(0).equals(toCell)).
                            findAny()
                    .orElseThrow(IllegalStateException::new);
            toIndex = list.indexOf(toRow);
        } else {
            toIndex = list.size();
        }

        return list.subList(list.indexOf(fromRow) + fromOffset, toIndex + toOffset);
    }

    public void loadMetadataTable() {
        List<List<Object>> readFile = readFile(5);
        subListByCell(readFile, "表元数据配置", "字段元数据配置", 2, 0).stream()
                .filter(row -> row.size() == 4)
                .forEach(this::toMetadataTable);
    }

    private void toMetadataTable(List<Object> row) {
        Dict dict = dictService.get(Example.of(Dict.builder().item(String.valueOf(row.get(MetaTableColumnIndex.NAME))).categoryEn(METADATA_CATEGORY).build()));
        if (dict == null)
            throw new IllegalStateException("未知资源: " + row);

        MetaTab metaTab = MetaTab.builder()
                .name(dict.getItem())
                .nameEn(String.valueOf(row.get(MetaTableColumnIndex.NAME_EN)))
                .displayType(StringUtils.hasText(String.valueOf(MetaTableColumnIndex.DISPLAY_TYPE)) ? 1 : null)
                .tabType(StringUtils.hasText(String.valueOf(MetaTableColumnIndex.TAB_TYPE)) ? String.valueOf(ResourceType.DATABASE_TABLE.getCode()) : null)
                .appId(APP_ID)
                .ownerId(OWNER_ID)
                .status(0)
                .deleted(DELETED)
                .build();

        MetaTab metaTabDb = metaTabService.get(Example.of(metaTab));
        if (metaTabDb == null) {
            metaTabService.add(metaTab);
        } else {
            dict.setId(metaTabDb.getId());
            metaTabService.update(metaTab);
        }
    }

    private static class DictColumnIndex {
        private static final int CATEGORY = 1;
        private static final int CATEGORY_EN = 2;
        private static final int ITEM = 3;
        private static final int ITEM_EN = 4;
        private static final int CVAL = 5;
        private static final int CVAL_EN = 6;
        private static final int LEVEL = 7;
        private static final int PARENT_NAME = 8;
    }

    private static class MetaFieldColumnIndex {
        private static final int NAME = 1;
        private static final int NAME_EN = 2;
        private static final int STORE_NAME = 3;
        private static final int STORE_NAME_EN = 4;
        private static final int TYPE = 5;
        private static final int OPTIONAL = 6;
        private static final int LIST_FIELD = 7;
        private static final int QUERY_FIELD = 8;
        private static final int DICT_FIELD = 9;
        private static final int CATEGORY_EN = 10;
        private static final int PRIMARY_KEY = 11;
        private static final int UNIQUE_INDEX = 12;
        private static final int IDX = 13;
    }

    private static class MetaTableColumnIndex {
        private static final int TAB_TYPE = 0;
        private static final int NAME = 1;
        private static final int NAME_EN = 2;
        private static final int DISPLAY_TYPE = 3;
    }
}
