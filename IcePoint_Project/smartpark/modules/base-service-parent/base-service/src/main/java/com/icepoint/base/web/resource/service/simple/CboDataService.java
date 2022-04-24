package com.icepoint.base.web.resource.service.simple;

import com.icepoint.base.api.vo.CboData;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public interface CboDataService {

    CboData getData();

}
