package com.github.tangyi.webcast.api.dto.resp;

import lombok.Data;

import java.util.List;

@Data
public class DataReportEcharts {

    private LiveListChannelSummaryWithAverageValueResponse liveListChannelSummary;

    private List<String> provinceX;

    private List<Long> provinceSeries;

}
