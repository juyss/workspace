package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.SmsRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * sms_record 短信发送记录
 *
 * @author xh
 * @since 2020/12/03
 */
@Mapper
public interface SmsRecordBaseMapper extends CommonDaoMapper<SmsRecord> {
}
