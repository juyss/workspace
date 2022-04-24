package com.icepoint.framework.workorder.work.service.impl;

import com.icepoint.framework.core.util.BeanUtils;
import com.icepoint.framework.core.util.CastUtils;
import com.icepoint.framework.core.util.SimpleTypeUtils;
import com.icepoint.framework.data.util.SqlUtils;
import com.icepoint.framework.web.core.support.ServiceSupport;
import com.icepoint.framework.workorder.config.agilebpm.AgileBpmAutoConfiguration;
import com.icepoint.framework.workorder.config.agilebpm.constant.TaskAction;
import com.icepoint.framework.workorder.config.agilebpm.model.AgileBpmResult;
import com.icepoint.framework.workorder.config.agilebpm.model.FlowRequestParameter;
import com.icepoint.framework.workorder.config.agilebpm.model.Task;
import com.icepoint.framework.workorder.work.dao.AgileBpmMapper;
import com.icepoint.framework.workorder.work.service.AgileBpmRestService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * @author Jiawei Zhao
 */
@Service
public class AgileBpmRestServiceImpl extends ServiceSupport implements AgileBpmRestService {

    private final RestTemplate restTemplate;

    private final AgileBpmMapper mapper;

    @Autowired
    public AgileBpmRestServiceImpl(
            @Qualifier(AgileBpmAutoConfiguration.REST_TEMPLATE) RestTemplate restTemplate,
            AgileBpmMapper mapper) {

        this.restTemplate = restTemplate;
        this.mapper = mapper;
    }

    @Override
    public String startNewFlowInstance(String defId, Object data) {

        String key = getInstanceKey(defId);

        Map<String, Object> map = convertDataToColumnValueMap(data);

        FlowRequestParameter parameter = new FlowRequestParameter(defId, "start", Collections.singletonMap(key, map));

        Logger logger = getLogger();
        boolean debugEnabled = logger.isDebugEnabled();
        if (debugEnabled) {
            logger.debug("发送启动流程操作请求, 流程定义id: \"{}\", 流程数据: \"{}\"", defId, map);
        }

        AgileBpmResult result = doInstanceAction(parameter);
        String instanceId = SimpleTypeUtils.parseString(result.getData());

        if (debugEnabled) {
            logger.debug("流程启动成功, 流程定义id: \"{}\", 流程实例id: \"{}\"", defId, instanceId);
        }

        return instanceId;
    }

    @Override
    public Optional<Task> getTaskByObjectId(Long objectId) {
        return mapper.getTaskIdByObjectId(objectId)
                .flatMap(this::getTaskById);
    }

    @Override
    public void doTaskAction(Task task, TaskAction action, String opinion) {
        String url = "/task/doAction";

        FlowRequestParameter parameter = new FlowRequestParameter(task.getId(), action.getName(), task.getData(), opinion);
        parameter.setDefId(task.getDefId());
        parameter.setInstanceId(task.getInstId());
        parameter.setNodeId(task.getNodeId());

        RequestEntity<FlowRequestParameter> entity = RequestEntity.post(url).body(parameter);
        doRequest(entity);
    }

    private Optional<Task> getTaskById(String id) {
        String url = "/task/getBpmTask?id={id}";
        RequestEntity<Void> entity = RequestEntity.get(url, id).build();

        return Optional.of(doRequest(entity))
                .map(AgileBpmResult::getData)
                .map(data -> BeanUtils.toBean(data, Task.class));
    }

    private Map<String, Object> convertDataToColumnValueMap(Object data) {

        Map<String, Object> map;
        if (data instanceof Map) {
            map = CastUtils.cast(data);
        } else {
            map = BeanUtils.toMap(data, true);
        }

        Map<String, Object> result = new LinkedHashMap<>();
        map.forEach((name, value) -> result.put(SqlUtils.toColumn(name), value));

        return result;
    }

    public String getInstanceKey(String defId) {
        String url = "/instance/getInstanceData?{defId}";
        RequestEntity<Void> entity = RequestEntity.get(url, defId).build();
        return Optional.ofNullable(doRequest(entity).getData())
                .map(map -> map.get("data"))
                .filter(Map.class::isInstance)
                .map(Map.class::cast)
                .map(Map::keySet)
                .map(Collection::iterator)
                .filter(Iterator::hasNext)
                .map(Iterator::next)
                .map(String::valueOf)
                .orElseThrow(() -> new IllegalArgumentException("获取实例key失败, def id: " + defId));
    }

    private AgileBpmResult doInstanceAction(Object body) {
        String url = "/instance/doAction";
        RequestEntity<Object> entity = RequestEntity.post(url).body(body);
        return doRequest(entity);
    }

    private AgileBpmResult doRequest(RequestEntity<?> entity) {

        Logger logger = getLogger();
        try {
            AgileBpmResult result = restTemplate.exchange(entity, AgileBpmResult.class).getBody();

            if (result != null && result.isOk()) {

                if (logger.isDebugEnabled()) {
                    logger.debug("AgileBpm请求成功, 返回信息: \"{}\"", result.getMsg());
                }

                return result;

            } else if (result == null) {
                throw new IllegalStateException("AgileBpm请求失败, 返回数据为空");
            } else {
                throw new IllegalStateException(String.format("AgileBpm请求失败, 异常码: %s, 返回信息: %s",
                        result.getCode(), result.getMsg()));
            }

        } catch (RestClientException ex) {
            throw new IllegalStateException("AgileBpm请求失败, RestClient抛出了异常", ex);
        }
    }

}
