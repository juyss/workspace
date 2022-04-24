package com.icepoint.base.web.entp.controller;

import com.github.tangyi.common.core.model.ResponseBean;
import com.github.tangyi.common.core.utils.ResponseBeanUtils;
import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.entity.Enterprise;
import com.icepoint.base.api.entity.MetaField;
import com.icepoint.base.api.entity.QueryCondition;
import com.icepoint.base.web.entp.service.EnterpriseService;
import com.icepoint.base.web.resource.component.query.FieldOperation;
import com.icepoint.base.web.resource.component.query.GenericQueryParameter;
import com.icepoint.base.web.resource.component.query.Match;
import com.icepoint.base.web.resource.component.query.Operation;
import com.icepoint.base.web.resource.service.complex.upper.GenericEntityService;
import com.icepoint.base.web.resource.service.simple.MetaFldService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "企业信息接口")
@RequestMapping("entp")
@RestController
@RequiredArgsConstructor
public class EnterpriseInfoController {

    private final GenericEntityService genericEntityService;
    private final MetaFldService metaFldService;
    private final EnterpriseService enterpriseService;

    /* 暂不提供
    @PutMapping("info")
    public ResponseBean<Boolean> update(@RequestBody Map<String, Object> entity) {
        return ResponseBeanUtils.updateData(genericEntityService.update("entInformation", entity));
    }*/

    @PostMapping("info")
    public ResponseBean<Boolean> updateByPost(@RequestBody Map<String, Object> entity) {
        return ResponseBeanUtils.updateData(genericEntityService.update("entInformation", entity));
    }

    /**
     * 获取字段元数据
     */
    @GetMapping("field")
    public ResponseBean<List<MetaField>> field() {
        MetaField metaField = new MetaField();
        metaField.setTabId(154L);
        metaField.setQueryField(1);
        metaField.setDeleted(0);
        return ResponseBeanUtils.queryMany(metaFldService.list(Example.of(metaField)));
    }

    /**
     * 高级查询
     */
    @PostMapping("list")
    public ResponseBean<List<GenericEntity>> list(@RequestBody List<QueryCondition> queryConditionList) {
        String key = "entInformation";
        SerializeType type = SerializeType.VALUE;
        GenericQueryParameter queryParameter = new GenericQueryParameter();

        Map<String, FieldOperation> fieldOps = new HashMap<>();
        Match match = new Match(fieldOps);
        for (QueryCondition queryCondition : queryConditionList) {
            Map<Operation, Object> ops = new LinkedHashMap<>();
            switch (queryCondition.getOptional()) {
                case "=":
                    ops.put(Operation.EQ, queryCondition.getValue());
                    break;
                case "!=":
                    ops.put(Operation.NEQ, queryCondition.getValue());
                    break;
                case ">":
                    ops.put(Operation.GT, queryCondition.getValue());
                    break;
                case ">=":
                    ops.put(Operation.GE, queryCondition.getValue());
                    break;
                case "<":
                    ops.put(Operation.LT, queryCondition.getValue());
                    break;
                case "<=":
                    ops.put(Operation.LE, queryCondition.getValue());
                    break;
                case "like":
                    ops.put(Operation.CONTAINS, queryCondition.getValue());
                    break;
            }
            FieldOperation fieldOperation = new FieldOperation(queryCondition.getConditional(), ops);
            fieldOps.put(queryCondition.getFiled(), fieldOperation);
        }
        queryParameter.setMatch(match);
        return ResponseBeanUtils.queryMany(genericEntityService.list(queryParameter, key, type));
    }

    /**
     * 高级查询
     */
    @PostMapping("advancedList")
    public ResponseBean<List<Enterprise>> advancedList(@RequestBody List<QueryCondition> queryConditionList) {
        return ResponseBeanUtils.queryMany(enterpriseService.list(queryConditionList));
    }

}
