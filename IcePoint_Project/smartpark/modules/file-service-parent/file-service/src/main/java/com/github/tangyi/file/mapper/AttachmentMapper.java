package com.github.tangyi.file.mapper;

import com.github.tangyi.common.core.persistence.CrudMapper;
import com.github.tangyi.file.api.model.Attachment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 附件mapper
 *
 */
@Mapper
public interface AttachmentMapper extends CrudMapper<Attachment> {
}
