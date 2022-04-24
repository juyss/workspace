package com.icepoint.base.web.resource.service.complex.upper;

import com.github.tangyi.common.basic.utils.excel.exception.ExcelException;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.vo.LastAndNest;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.metadata.ResourceType;
import com.icepoint.base.web.resource.component.query.QueryParameter;
import com.icepoint.base.web.resource.repository.GenericEntityServiceSupports;
import com.icepoint.base.web.resource.repository.GenericRepository;
import com.icepoint.base.web.resource.repository.GenericRepositoryAdapter;
import com.icepoint.base.web.resource.service.complex.ResourceMetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class GenericEntityServiceImpl implements GenericEntityService,
        GenericEntityServiceSupports {

    private final ResourceMetadataService resourceMetadataService;
    private final GenericRepositoryAdapter repositoryAdapter;

    @Override
    public GenericEntity get(QueryParameter queryParameter, String key, Object id, SerializeType serializeType) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        return repository.get(queryParameter, id, metadata, serializeType);
    }

    @Override
    public List<GenericEntity> list(QueryParameter queryParameter, String key, SerializeType serializeType) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        return repository.list(queryParameter, metadata, serializeType);
    }

    @Override
    public Page<GenericEntity> page(QueryParameter queryParameter, String key, SerializeType serializeType, Pageable pageable) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        return repository.page(queryParameter, metadata, serializeType, pageable);
    }

    @Override
    public synchronized GenericEntity add(String key, Map<String, Object> entity) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        return repository.add(entity, metadata);
    }

    @Override
    public int push(String key, List<Map<String, Object>> entityList) {
        for (Map<String, Object> entity : entityList) {
            ResourceMetadata metadata = getResourceMetadata(key);
            GenericRepository repository = getRepository(metadata);
            repository.add(entity, metadata);
        }
        return entityList.size();
    }

    @Override
    public int update(String key, Map<String, Object> entity) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        return repository.update(entity, metadata);
    }

    @Override
    public int delete(String key, List<Object> idList) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        return repository.deleteAll(idList, metadata);
    }

    @Override
    public int approval(String key, Map<String, Object> entity) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        return repository.approval(entity, metadata);
    }

    @Override
    public void download(HttpServletRequest request, HttpServletResponse response, String key, List<String> docNoList) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        repository.download(request, response, key, docNoList);
    }

    @Override
    public void excel(String key, MultipartFile excel) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        List<Object> excelList = repository.excel(metadata, key, excel);
        if (excelList != null) {
            for (Object excelItem : excelList) {
                try {
                    add(key, objectToMap(excelItem));
                } catch (IllegalAccessException e) {
                    throw new ExcelException("导入失败");
                }
            }
        }
    }

    @Override
    public List<GenericEntity> geom(QueryParameter queryParameter, String key, SerializeType serializeType, Integer geomType) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        return repository.geom(queryParameter, metadata, serializeType, geomType);
    }

    @Override
    public LastAndNest lastAndNest(String id, QueryParameter queryParameter, String key, SerializeType serializeType, Pageable pageable) {
        ResourceMetadata metadata = getResourceMetadata(key);
        GenericRepository repository = getRepository(metadata);
        List<GenericEntity> content = repository.page(queryParameter, metadata, serializeType, PageRequest.of(0, 99999, pageable.getSort())).getContent();
        LastAndNest result = new LastAndNest();
        String titleFieldName = "title";
        String createTimeFieldName = "createTime";
        switch (key) {
            case "cboPolicy":
                titleFieldName = "PolicyName";
                break;
            case "discountEnt":
                titleFieldName = "preferenceItem";
                break;
            case "lndustryInformation":
                titleFieldName = "name";
                createTimeFieldName = "releaseTime";
                break;
            case "resultsReport":
                titleFieldName = "name";
                break;
            case "cboItem":
                titleFieldName = "projectName";
                break;
            case "accidentCases":
                titleFieldName = "address";
                break;
            case "dangChemicals":
                titleFieldName = "name";
                break;
            case "unitFunction":
                titleFieldName = "pollutionSources";
                break;
        }
        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).getPropertyValue("id").toString().equals(id)) {
                if (i > 0) {
                    GenericEntity last = content.get(i - 1);
                    result.setLastId(last.getPropertyValue("id").toString());
                    result.setLastTitle(last.getPropertyValue(titleFieldName).toString());
                    result.setLastDate(last.getPropertyValue(createTimeFieldName).toString());
                }
                if (i < content.size() - 1) {
                    GenericEntity next = content.get(i + 1);
                    result.setNestId(next.getPropertyValue("id").toString());
                    result.setNestTitle(next.getPropertyValue(titleFieldName).toString());
                    result.setNestDate(next.getPropertyValue(createTimeFieldName).toString());
                }
            }
        }
        return result;
    }


    private ResourceMetadata getResourceMetadata(String key) {
        ResourceMetadata metadata = resourceMetadataService.get(key);
        Assert.notNull(metadata, "找不到资源元数据信息");
        return metadata;
    }

    private GenericRepository getRepository(ResourceMetadata metadata) {
        ResourceType resourceType = ResourceType.valueOf(metadata.getResource().getResType());
        return repositoryAdapter.get(resourceType);
    }

    private Map<String, Object> objectToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(obj);
            map.put(fieldName, value);
        }
        return map;
    }
}
