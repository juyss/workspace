package com.icepoint.base.web.entp.service.impl;

import com.icepoint.base.api.entity.Enterprise;
import com.icepoint.base.api.entity.QueryCondition;
import com.icepoint.base.web.basic.service.AntdPageService;
import com.icepoint.base.web.entp.mapper.EnterpriseMapper;
import com.icepoint.base.web.entp.service.EnterpriseService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnterpriseServiceImpl extends AntdPageService<EnterpriseMapper, Enterprise, Long> implements EnterpriseService {
    @Override
    public List<Enterprise> list(List<QueryCondition> queryConditionList) {
        String sql = "SELECT id,companyName,companyShortName,creditCode,creditStartDate,creditExpiryDate,companyCategory,corporateScale,business,x,y,companyRegisterAddress,companyAdress,mailBox,officialAddress,operatingStatus,foundDate,industry,mainProducts,corporateDelegate,delegatePhone,telephone,leaderName,leaderTel,companyPeople,amapX,amapY,orderNo FROM ent_enterprise_base";
        StringBuilder whereSql = new StringBuilder();
        StringBuilder orConditionSql = new StringBuilder();
        if (!CollectionUtils.isEmpty(queryConditionList)) {
            List<QueryCondition> andConditionList = queryConditionList.stream().filter(queryCondition -> "0".equals(queryCondition.getConditional())).collect(Collectors.toList());
            List<QueryCondition> orConditionList = queryConditionList.stream().filter(queryCondition -> "1".equals(queryCondition.getConditional())).collect(Collectors.toList());

            whereSql.append(" WHERE ");

            if (!CollectionUtils.isEmpty(andConditionList)) {
                whereSql.append(" 1 = 1 ");
            }
            for (int i = 0; i < andConditionList.size(); i++) {
                whereSql.append("AND ");
                whereSql.append(andConditionList.get(i).getFiled());
                whereSql.append(" ");
                whereSql.append(andConditionList.get(i).getOptional());
                if ("like".equals(andConditionList.get(i).getOptional())) {
                    whereSql.append(" \'%");
                    whereSql.append(andConditionList.get(i).getValue());
                    whereSql.append("%\' ");
                } else {
                    whereSql.append(" \'");
                    whereSql.append(andConditionList.get(i).getValue());
                    whereSql.append("\' ");
                }
            }

            for (int i = 0; i < orConditionList.size(); i++) {
                orConditionSql.append("OR ");
                orConditionSql.append(orConditionList.get(i).getFiled());
                orConditionSql.append(" ");
                orConditionSql.append(orConditionList.get(i).getOptional());
                if ("like".equals(orConditionList.get(i).getOptional())) {
                    orConditionSql.append(" \'%");
                    orConditionSql.append(orConditionList.get(i).getValue());
                    orConditionSql.append("%\' ");
                } else {
                    orConditionSql.append(" \'");
                    orConditionSql.append(orConditionList.get(i).getValue());
                    orConditionSql.append("\' ");
                }
            }

            if (CollectionUtils.isEmpty(andConditionList)) {
                orConditionSql.delete(0, 2);
            }
        }
        sql = sql + whereSql.toString() + orConditionSql.toString();
        return mapper.listBySql(sql);
    }
}
