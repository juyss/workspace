package com.github.tangyi.webcast.service;

import com.github.tangyi.common.core.service.CrudService;
import com.github.tangyi.webcast.api.model.DataReport;
import com.github.tangyi.webcast.mapper.DataReportMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class DataReportService extends CrudService<DataReportMapper, DataReport> {

    public String latestChannelId() {
        List<DataReport> allList = this.findAllList(null);
        return allList.get(allList.size() - 1).getChannelId();
    }

}
