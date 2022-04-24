package com.icepoint.base.web.resource.service.simple.impl;

import com.icepoint.base.api.domain.GenericEntity;
import com.icepoint.base.api.domain.SerializeType;
import com.icepoint.base.api.vo.LaborNeedData;
import com.icepoint.base.web.resource.component.query.*;
import com.icepoint.base.web.resource.service.complex.upper.GenericEntityService;
import com.icepoint.base.web.resource.service.simple.LaborNeedDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaborNeedDataServiceImpl implements LaborNeedDataService {

    @Autowired
    private GenericEntityService genericEntityService;

    @Override
    public LaborNeedData getLineData() {
        LocalDate nowLocalDate = LocalDate.now();

        LaborNeedData laborNeedData = new LaborNeedData();
        List<String> xAxis = new ArrayList<>();
        List<Integer> series = new ArrayList<>(nowLocalDate.getMonthValue());
        for (int i = 1; i <= nowLocalDate.getMonthValue(); i++) {
            xAxis.add(i + "月");
            series.add(0);
        }
        laborNeedData.setXAxis(xAxis);
        laborNeedData.setSeries(series);

        QueryParameter queryParameter = new GenericQueryParameter();
        Map<String, FieldOperation> fieldOps = new LinkedHashMap<>();
        Match match = new Match(fieldOps);

        Map<Operation, Object> stateOps = new LinkedHashMap<>();
        stateOps.put(Operation.EQ, 2);
        FieldOperation state = new FieldOperation("open", stateOps);
        fieldOps.put("open", state);

        Map<Operation, Object> deletedOps = new LinkedHashMap<>();
        deletedOps.put(Operation.EQ, 0);
        FieldOperation deleted = new FieldOperation("deleted", deletedOps);
        fieldOps.put("deleted", deleted);

        ((GenericQueryParameter) queryParameter).setMatch(match);
        List<GenericEntity> genericEntityList = genericEntityService.page(queryParameter, "laborNeed", SerializeType.VALUE, PageRequest.of(0, 999)).getContent();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (int i = 1; i <= nowLocalDate.getMonthValue(); i++) {
            int finalI = i;
            genericEntityList.stream().forEach(genericProperties -> {
                LocalDate releaseTime = LocalDate.parse(genericProperties.getPropertyValue("releaseTime").toString(), dateTimeFormatter);
                if (releaseTime.getYear() == nowLocalDate.getYear()) {
                    if (releaseTime.getMonthValue() == finalI) {
                        series.set(finalI - 1, series.get(finalI - 1) + Integer.valueOf(genericProperties.getPropertyValue("quantityDemanded").toString()));
                    }
                }
            });
        }

        return laborNeedData;
    }

}
