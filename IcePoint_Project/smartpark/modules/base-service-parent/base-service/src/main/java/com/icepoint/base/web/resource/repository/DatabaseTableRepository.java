package com.icepoint.base.web.resource.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tangyi.common.core.exceptions.ServiceException;
import com.huawei.it.eip.ump.client.producer.Producer;
import com.huawei.it.eip.ump.client.producer.SendResult;
import com.huawei.it.eip.ump.common.exception.UmpException;
import com.huawei.it.eip.ump.common.message.Message;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.MapBasedEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.domain.ValueGenericProperty;
import com.icepoint.base.api.entity.Approval;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.excel.DisposalMethodExcel;
import com.icepoint.base.api.excel.UnitFunctionExcel;
import com.icepoint.base.api.util.StreamUtils;
import com.icepoint.base.kafka.ProducerProperties;
import com.icepoint.base.mqs.MqsProducerProperties;
import com.icepoint.base.web.resource.component.generic.DatabaseTableParam;
import com.icepoint.base.web.resource.component.metadata.ResourceMetadata;
import com.icepoint.base.web.resource.component.metadata.ResourceType;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.mapper.DatabaseTableMapper;
import com.icepoint.base.web.resource.runnable.RomaProducerThread;
import com.icepoint.base.web.resource.service.simple.ApprovalService;
import com.icepoint.base.web.resource.util.ExcelUtil;
import com.icepoint.base.web.resource.util.GenericUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

/**
 * @author Jiawei Zhao
 */
@Component
@RequiredArgsConstructor
public class DatabaseTableRepository extends GenericRepositoryTemplate {

    private final DatabaseTableMapper mapper;

    private final ApprovalService approvalService;

    private final ProducerProperties producerProperties;

    private final MqsProducerProperties mqsProducerProperties;

    private final Producer producer;

    @Override
    public ResourceType getResourceType() {
        return ResourceType.DATABASE_TABLE;
    }

    @Override
    public GenericEntity doGet(QueryParameter queryParameter, Object objectId, ResourceMetadata metadata, SerializeType serializeType) {
        DatabaseTableParam param = DatabaseTableParam
                .selectFrom(metadata)
                .where().field("id", objectId).build();

        List<Map<String, Object>> dataMapList = mapper.list(param, null);

        return CollectionUtils.isEmpty(dataMapList)
                ? null
                : getRequiredSingleElement(GenericUtils.toEntitiesFromMaps(metadata, dataMapList, serializeType));
    }

    @Override
    public List<GenericEntity> doList(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType) {
        DatabaseTableParam param = DatabaseTableParam.selectFrom(metadata).build();
        AtomicBoolean hadDeletedField = new AtomicBoolean(false);
        param.getSelectFields().forEach(selectField -> {
            if ("deleted".equals(selectField.getName())) {
                hadDeletedField.set(true);
                return;
            }
        });
        if (((GenericQueryParameter) queryParameter).getMatch() == null && hadDeletedField.get()) {
            GenericQueryParameter genericQueryParameter = new GenericQueryParameter();
            Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
            Match match = new Match(fieldOps);

            Map<Operation, Object> ops = new LinkedHashMap<>();
            ops.put(Operation.EQ, 0);
            FieldOperation fieldOperation = new FieldOperation("deleted", ops);
            fieldOps.put("deleted", fieldOperation);

            genericQueryParameter.setMatch(match);
            queryParameter = genericQueryParameter;
        }
        List<Map<String, Object>> dataMapList = mapper.list(param, queryParameter);

        return CollectionUtils.isEmpty(dataMapList)
                ? Collections.emptyList()
                : GenericUtils.toEntitiesFromMaps(metadata, dataMapList, serializeType);
    }

    @Override
    public Page<GenericEntity> doPage(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType, Pageable pageable) {
        DatabaseTableParam param = DatabaseTableParam.selectFrom(metadata).build();
        Page<Map<String, Object>> page = mapper.page(param, queryParameter, pageable);

        return page.isEmpty()
                ? Page.empty()
                : PageableExecutionUtils.getPage(
                GenericUtils.toEntitiesFromMaps(metadata, page.getContent(), serializeType), pageable, page::getTotalElements);
    }

    @Override
    public MapBasedEntity doAdd(Map<String, Object> entity, ResourceMetadata metadata) {
        //插入字段
        List<MetaField> mFlds = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> entity.containsKey(metaField.getNameEn()))
                .collect(Collectors.toList());

        //插入值
        List<DatabaseTableParam.ValueField> vFlds = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> entity.containsKey(metaField.getNameEn()))
                .map(metaField -> {
                    DatabaseTableParam.ValueField field = new DatabaseTableParam.ValueField();
                    field.setName(metaField.getStoreNameEn());
                    field.setValue(entity.get(metaField.getNameEn()));
                    return field;
                })
                .collect(Collectors.toList());

        DatabaseTableParam param = DatabaseTableParam.insertFrom(mFlds,
                metadata.getMetaTab().getNameEn()).build();
        param.setValueFields(vFlds);
        mapper.save(param);
        Assert.isTrue(param.getId() > 0, "插入记录失败");

        MapBasedEntity result = new MapBasedEntity(metadata.getKey(), metadata.getMetaTab());

        MetaField pkMetaField = metadata.getMetaFields().stream()
                .filter(metaField -> metaField.getPrimaryKey() == 1)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("缺少主键字段元数据信息"));
        result.setProperty(new ValueGenericProperty(pkMetaField.getNameEn(), param.getId()));

        vFlds.forEach(vf -> {

            String propertyName = vf.getName();
            ValueGenericProperty property = new ValueGenericProperty(
                    propertyName,
                    parsePrimitive(vf.getValue(), String.class)
            );
            result.setProperty(property);
        });

        if ("entInformation".equals(metadata.getKey())) {
            // 发送消息 公有云
            /*DmsProducer<Object, Object> producer = new DmsProducer<>();
            int partition = 0;
            try {
                String key = result.getProperty("id").getValue().toString();
                ObjectMapper mapper = new ObjectMapper();
                entity.remove("appId");
                entity.remove("ownerId");
                entity.remove("createUser");
                entity.remove("updateUser");
                entity.remove("deleted");
                entity.put("id",key);
                String data = mapper.writeValueAsString(entity);
                producer.produce(producerProperties.getEnterpriseTopic(), partition, key, data, new Callback() {
                    public void onCompletion(RecordMetadata metadata,
                                             Exception exception) {
                        if (exception != null) {
                            exception.printStackTrace();
                            return;
                        }
                        System.out.println("produce msg completed");
                    }
                });
                System.out.println("produce msg:" + data);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                producer.close();
            }*/
            // 发送消息 私有云
            /*try {
                String key = result.getProperty("id").getValue().toString();
                ObjectMapper mapper = new ObjectMapper();
                entity.remove("appId");
                entity.remove("ownerId");
                entity.remove("createUser");
                entity.remove("updateUser");
                entity.remove("deleted");
                entity.put("id", key);
                String data = mapper.writeValueAsString(entity);
                Message message = new Message();
                message.setBusinessId(mqsProducerProperties.getBusinessId()); // 设置消息业务标示，便于追踪消息轨迹
                message.setTags(mqsProducerProperties.getTag()); // 设置消息标签
                message.setBody(data.getBytes("UTF-8"));
                SendResult sendResult = producer.send(message);
                if (sendResult.isSuccess()) {
                    // 发送成功的逻辑处理
                    System.out.println("message++++++" + sendResult.getMessageId());
                } else {
                    // 发送失败的逻辑处理
                    System.out.println(sendResult.toString());
                }
            } catch (UmpException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }*/
            new Thread(new RomaProducerThread(result, entity, mqsProducerProperties, producer)).start();
        }
        return result;
    }

    @Override
    public int doUpdate(Map<String, Object> entity, ResourceMetadata metadata) {
        //条件主键
        List<MetaField> pkFlds = StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                .filter(metaField -> 1 == metaField.getPrimaryKey())
                .collect(Collectors.toList());
        Assert.isTrue(1 == pkFlds.size(), "必填字段缺少值");
        MetaField fld = pkFlds.get(0);

        List<DatabaseTableParam.WhereField> whereFields = new ArrayList<>();
        DatabaseTableParam.WhereField field = new DatabaseTableParam.WhereField();
        field.setName(fld.getStoreNameEn());
        field.setValue(entity.get(fld.getNameEn()));
        whereFields.add(field);

        //更新值
        DatabaseTableParam param = DatabaseTableParam.valueFrom(StreamUtils.parallelStreamIfAvailable(metadata.getMetaFields())
                        .filter(metaField -> 1 != metaField.getPrimaryKey() && entity.containsKey(metaField.getNameEn()))
                        .map(metaField -> {
                            DatabaseTableParam.ValueField vfield = new DatabaseTableParam.ValueField();
                            vfield.setName(metaField.getStoreNameEn());
                            vfield.setValue(entity.get(metaField.getNameEn()));
                            return vfield;
                        })
                        .collect(Collectors.toList()),
                metadata.getMetaTab().getNameEn()).build();
        param.setWhereFields(whereFields);
        int result = mapper.update(param);

        if ("entInformation".equals(metadata.getKey())) {
            // 发送消息 公有云
            /*DmsProducer<Object, Object> producer = new DmsProducer<>();
            int partition = 0;
            try {
                String key = entity.get("id").toString();
                ObjectMapper mapper = new ObjectMapper();
                entity.remove("appId");
                entity.remove("ownerId");
                entity.remove("createUser");
                entity.remove("updateUser");
                entity.remove("deleted");
                String data = mapper.writeValueAsString(entity);
                producer.produce(producerProperties.getEnterpriseTopic(), partition, key, data, new Callback() {
                    public void onCompletion(RecordMetadata metadata,
                                             Exception exception) {
                        if (exception != null) {
                            exception.printStackTrace();
                            return;
                        }
                        System.out.println("produce msg completed");
                    }
                });
                System.out.println("produce msg:" + data);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                producer.close();
            }*/
            // 发送消息 私有云
            try {
                String key = entity.get("id").toString();
                ObjectMapper mapper = new ObjectMapper();
                entity.remove("appId");
                entity.remove("ownerId");
                entity.remove("createUser");
                entity.remove("updateUser");
                entity.remove("deleted");
                String data = mapper.writeValueAsString(entity);
                Message message = new Message();
                message.setBusinessId(mqsProducerProperties.getBusinessId()); // 设置消息业务标示，便于追踪消息轨迹
                message.setTags(mqsProducerProperties.getTag()); // 设置消息标签
                message.setBody(data.getBytes("UTF-8"));
                SendResult sendResult = producer.send(message);
                if (sendResult.isSuccess()) {
                    // 发送成功的逻辑处理
                    System.out.println("message++++++" + sendResult.getMessageId());
                } else {
                    // 发送失败的逻辑处理
                    System.out.println(sendResult.toString());
                }
            } catch (UmpException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    protected int doDelete(Object id, ResourceMetadata metadata) {
        MetaField primaryKeyField = getRequiredSingleElement(metadata.getMetaFields().stream()
                .filter(metaField -> 1 == metaField.getPrimaryKey())
                .collect(Collectors.toList()));

        DatabaseTableParam.ValuesField field = new DatabaseTableParam.ValuesField();
        field.setName(primaryKeyField.getStoreNameEn());
        field.setValues(Collections.singletonList(id));

        DatabaseTableParam param = DatabaseTableParam.deleteFrom(field, metadata.getMetaTab().getNameEn()).build();
        return mapper.delete(param);
    }

    @Override
    protected int doDeleteAll(List<Object> id, ResourceMetadata metadata) {
        MetaField primaryKeyField = getRequiredSingleElement(metadata.getMetaFields().stream()
                .filter(metaField -> 1 == metaField.getPrimaryKey())
                .collect(Collectors.toList()));

        DatabaseTableParam.ValuesField field = new DatabaseTableParam.ValuesField();
        field.setName(primaryKeyField.getStoreNameEn());
        field.setValues(id);

        DatabaseTableParam param = DatabaseTableParam.deleteFrom(field, metadata.getMetaTab().getNameEn()).build();
        return mapper.delete(param);
    }

    @Override
    protected List<GenericEntity> doGeom(QueryParameter queryParameter, ResourceMetadata metadata, SerializeType serializeType) {
        return null;
    }

    @Override
    public int approval(Map<String, Object> entity, ResourceMetadata metadata) {
        Approval approval = new Approval();
        approval.setDataId(new Long(entity.get("id").toString()));
        approval.setOperateTime(new Date());
        approval.setOperate(entity.get("operate").toString());
        approval.setOperator(entity.get("operator").toString());
        approval.setAuditOpinion(entity.get("auditOpinion").toString());
        switch (metadata.getKey()) {
            // 消息推送
            case "affairOpen":
                approval.setTabId(74L);
                break;
            // 招商政策
            case "cboPolicy":
                approval.setTabId(24L);
                break;
            // 招商项目
            case "cboItem":
                approval.setTabId(25L);
                break;
            // 招商动态
            case "cboDynamic":
                approval.setTabId(26L);
                break;
            default:
                throw new ServiceException("找不到Key值对应的表");
        }
        approvalService.add(approval);
        return doUpdate(entity, metadata);
    }

    @Override
    public List<Object> excel(ResourceMetadata metadata, String key, MultipartFile excel) {
        if ("disposalMethod".equals(key)) {
            return ExcelUtil.readExcel(excel, new DisposalMethodExcel(), 1, 1);
        }
        if ("unitFunction".equals(key)) {
            return ExcelUtil.readExcel(excel, new UnitFunctionExcel(), 1, 1);
        }
        return null;
    }
}
