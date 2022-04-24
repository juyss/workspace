package com.github.tangyi.mapper;

import com.github.tangyi.core.mybatis.mapper.CommonDaoMapper;
import com.github.tangyi.model.TPushAuditRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * t_push_audit_record 信息推送审核记录表
 *
 * @author jy
 * @since 2020/11/03
 */
@Mapper
public interface TPushAuditRecordBaseMapper extends CommonDaoMapper<TPushAuditRecord> {
}
