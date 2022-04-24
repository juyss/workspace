package com.icepoint.base.web.park.service;

import com.icepoint.base.api.vo.ParkData;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface DataService {

    ParkData getParkDataYearEcharts(Integer startYear, Integer endYear);

    ParkData getParkDataQuarterEcharts(Integer startYear, Integer endYear, Integer quarter);
}
