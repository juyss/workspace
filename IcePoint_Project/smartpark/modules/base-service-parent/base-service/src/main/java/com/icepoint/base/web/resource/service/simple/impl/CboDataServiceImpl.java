package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.vo.CboData;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.service.simple.CboDataService;
import com.icepoint.base.web.resource.service.complex.upper.GenericEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class CboDataServiceImpl implements CboDataService {

    @Autowired
    private GenericEntityService genericEntityService;

    @Override
    public CboData getData() {
        LocalDateTime monthStartDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).withDayOfMonth(1);

        CboData cboData = new CboData();
        // 查询 招商政策总数
        long cboPolicyTotalElements = genericEntityService.page(new GenericQueryParameter(), "cboPolicy", SerializeType.VALUE,
                PageRequest.of(0, 999)).getTotalElements();
        // 查询 招商政策已审核数
        QueryParameter queryParameter = new GenericQueryParameter();
        Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
        Match match = new Match(fieldOps);

        Map<Operation, Object> stateOps = new LinkedHashMap<>();
        stateOps.put(Operation.EQ, 2);
        FieldOperation state = new FieldOperation("state", stateOps);
        fieldOps.put("state", state);

        Map<Operation, Object> deletedOps = new LinkedHashMap<>();
        deletedOps.put(Operation.EQ, 0);
        FieldOperation deleted = new FieldOperation("deleted", deletedOps);
        fieldOps.put("deleted", deleted);

        ((GenericQueryParameter) queryParameter).setMatch(match);
        long cboPolicyApproved = genericEntityService.page(queryParameter, "cboPolicy", SerializeType.VALUE, PageRequest.of(0, 999)).getTotalElements();
        // 设置招商政策完成率
        cboData.setPolicyCompletionRate(cboPolicyTotalElements == 0 ? 0 : ((double) cboPolicyApproved) / cboPolicyTotalElements);


        Map<Operation, Object> operateTimeOps = new LinkedHashMap<>();
        operateTimeOps.put(Operation.GE, monthStartDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        FieldOperation operateTime = new FieldOperation("operateTime", operateTimeOps);
        fieldOps.put("operateTime", operateTime);
        ((GenericQueryParameter) queryParameter).setMatch(match);
        cboData.setPolicyApproved((int) genericEntityService.page(queryParameter, "cboPolicy", SerializeType.VALUE, PageRequest.of(0, 999)).getTotalElements());


        cboData.setItemCompletionRate(getItemCompletionRate());
        cboData.setItemApproved(getItemApproved());
        cboData.setDynamicCompletionRate(getDynamicCompletionRate());
        cboData.setDynamicApproved(getDynamicApproved());


        QueryParameter monthlyQueryParameter = new GenericQueryParameter();
        Map<String, FieldOperation> monthlyFieldOps = new LinkedHashMap<>();
        Match monthlyMatch = new Match(monthlyFieldOps);

        monthlyFieldOps.put("deleted", deleted);

        Map<Operation, Object> createTimeOps = new LinkedHashMap<>();
        createTimeOps.put(Operation.GE, monthStartDate.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        FieldOperation createTimeFo = new FieldOperation("createTime", createTimeOps);
        monthlyFieldOps.put("createTime", createTimeFo);
        ((GenericQueryParameter) monthlyQueryParameter).setMatch(monthlyMatch);

        cboData.setMonthlyPolicy(getMonthlyPolicy(monthlyQueryParameter));
        cboData.setMonthlyItem(getMonthlyItem(monthlyQueryParameter));
        cboData.setMonthlyDynamic(getMonthlyDynamic(monthlyQueryParameter));

        return cboData;
    }

    private Long getMonthlyPolicy(QueryParameter monthlyQueryParameter) {
        return genericEntityService.page(monthlyQueryParameter, "cboPolicy", SerializeType.VALUE, PageRequest.of(0, 9999)).getTotalElements();
    }

    private Long getMonthlyItem(QueryParameter monthlyQueryParameter) {
        return genericEntityService.page(monthlyQueryParameter, "cboItem", SerializeType.VALUE, PageRequest.of(0, 9999)).getTotalElements();
    }


    private Long getMonthlyDynamic(QueryParameter monthlyQueryParameter) {
        return genericEntityService.page(monthlyQueryParameter, "cboDynamic", SerializeType.VALUE, PageRequest.of(0, 9999)).getTotalElements();
    }


    private Integer getDynamicApproved() {
        LocalDateTime monthStartDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).withDayOfMonth(1);

        QueryParameter queryParameter = new GenericQueryParameter();
        Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
        Match match = new Match(fieldOps);

        Map<Operation, Object> stateOps = new LinkedHashMap<>();
        stateOps.put(Operation.EQ, 2);
        FieldOperation state = new FieldOperation("state", stateOps);
        fieldOps.put("state", state);

        Map<Operation, Object> deletedOps = new LinkedHashMap<>();
        deletedOps.put(Operation.EQ, 0);
        FieldOperation deleted = new FieldOperation("deleted", deletedOps);
        fieldOps.put("deleted", deleted);

        Map<Operation, Object> operateTimeOps = new LinkedHashMap<>();
        operateTimeOps.put(Operation.GE, monthStartDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        FieldOperation operateTime = new FieldOperation("operateTime", operateTimeOps);
        fieldOps.put("operateTime", operateTime);
        ((GenericQueryParameter) queryParameter).setMatch(match);
        return (int) genericEntityService.page(queryParameter, "cboDynamic", SerializeType.VALUE, PageRequest.of(0, 999)).getTotalElements();
    }

    private Double getDynamicCompletionRate() {
        // 查询 招商项目总数
        long cboPolicyTotalElements = genericEntityService.page(new GenericQueryParameter(), "cboDynamic", SerializeType.VALUE,
                PageRequest.of(0, 999)).getTotalElements();
        // 查询 招商项目已审核数
        QueryParameter queryParameter = new GenericQueryParameter();
        Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
        Match match = new Match(fieldOps);

        Map<Operation, Object> stateOps = new LinkedHashMap<>();
        stateOps.put(Operation.EQ, 2);
        FieldOperation state = new FieldOperation("state", stateOps);
        fieldOps.put("state", state);

        Map<Operation, Object> deletedOps = new LinkedHashMap<>();
        deletedOps.put(Operation.EQ, 0);
        FieldOperation deleted = new FieldOperation("deleted", deletedOps);
        fieldOps.put("deleted", deleted);

        ((GenericQueryParameter) queryParameter).setMatch(match);
        long cboPolicyApproved = genericEntityService.page(queryParameter, "cboDynamic", SerializeType.VALUE, PageRequest.of(0, 999)).getTotalElements();
        // 设置招商项目完成率
        return cboPolicyTotalElements == 0 ? 0 : ((double) cboPolicyApproved) / cboPolicyTotalElements;
    }

    private Integer getItemApproved() {
        LocalDateTime monthStartDate = LocalDateTime.of(LocalDate.now(), LocalTime.MIN).withDayOfMonth(1);

        QueryParameter queryParameter = new GenericQueryParameter();
        Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
        Match match = new Match(fieldOps);

        Map<Operation, Object> stateOps = new LinkedHashMap<>();
        stateOps.put(Operation.EQ, 2);
        FieldOperation state = new FieldOperation("state", stateOps);
        fieldOps.put("state", state);

        Map<Operation, Object> deletedOps = new LinkedHashMap<>();
        deletedOps.put(Operation.EQ, 0);
        FieldOperation deleted = new FieldOperation("deleted", deletedOps);
        fieldOps.put("deleted", deleted);

        /*Map<Operation, Object> operateTimeOps = new LinkedHashMap<>();
        operateTimeOps.put(Operation.GE, monthStartDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        FieldOperation operateTime = new FieldOperation("operateTime", operateTimeOps);
        fieldOps.put("operateTime", operateTime);*/
        Map<Operation, Object> createTimeOps = new LinkedHashMap<>();
        createTimeOps.put(Operation.GE, monthStartDate.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        FieldOperation createTimeFo = new FieldOperation("createTime", createTimeOps);
        fieldOps.put("createTime", createTimeFo);
        ((GenericQueryParameter) queryParameter).setMatch(match);
        return (int) genericEntityService.page(queryParameter, "cboPolicy", SerializeType.VALUE, PageRequest.of(0, 999)).getTotalElements();
    }

    private Double getItemCompletionRate() {
        // 查询 招商项目总数
        long cboPolicyTotalElements = genericEntityService.page(new GenericQueryParameter(), "cboItem", SerializeType.VALUE,
                PageRequest.of(0, 999)).getTotalElements();
        // 查询 招商项目已审核数
        QueryParameter queryParameter = new GenericQueryParameter();
        Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
        Match match = new Match(fieldOps);

        Map<Operation, Object> stateOps = new LinkedHashMap<>();
        stateOps.put(Operation.EQ, 2);
        FieldOperation state = new FieldOperation("state", stateOps);
        fieldOps.put("state", state);

        Map<Operation, Object> deletedOps = new LinkedHashMap<>();
        deletedOps.put(Operation.EQ, 0);
        FieldOperation deleted = new FieldOperation("deleted", deletedOps);
        fieldOps.put("deleted", deleted);

        ((GenericQueryParameter) queryParameter).setMatch(match);
        long cboPolicyApproved = genericEntityService.page(queryParameter, "cboItem", SerializeType.VALUE, PageRequest.of(0, 999)).getTotalElements();
        // 设置招商项目完成率
        return cboPolicyApproved == 0 ? 0 : ((double) cboPolicyApproved) / cboPolicyTotalElements;
    }

}
