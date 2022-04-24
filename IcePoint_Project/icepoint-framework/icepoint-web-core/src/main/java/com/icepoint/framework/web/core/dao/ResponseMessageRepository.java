package com.icepoint.framework.web.core.dao;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.web.core.entity.ResponseMessage;
import org.springframework.stereotype.Repository;

/**
 * @author Jiawei Zhao
 */
@Repository
public interface ResponseMessageRepository extends LongStdRepository<ResponseMessage> {
}
