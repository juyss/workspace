package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.api.vo.LaborNeedData;
import com.icepoint.base.api.vo.ParkData;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface LaborNeedDataService {

    LaborNeedData getLineData();
}
