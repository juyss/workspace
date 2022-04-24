package com.icepoint.framework.workorder.work.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * @author Jiawei Zhao
 */
@Mapper
public interface AgileBpmMapper {

    Optional<String> getTaskIdByObjectId(@Param("ObjectId") Long ObjectId);
}
