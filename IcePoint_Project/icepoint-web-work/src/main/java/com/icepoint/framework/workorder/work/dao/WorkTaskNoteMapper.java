package com.icepoint.framework.workorder.work.dao;

import com.icepoint.framework.data.mybatis.RepositoryMapper;
import com.icepoint.framework.web.core.rest.MultiplyPathRepositoryRestResource;
import com.icepoint.framework.workorder.work.entity.WorkTaskNote;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName WorkTaskNoteMapper
 * @description
 * @since 2021-07-16 15:04
 */
@MultiplyPathRepositoryRestResource(path = "/workOrder/taskNote")
@Mapper
public interface WorkTaskNoteMapper extends RepositoryMapper<WorkTaskNote, Long> {
}
