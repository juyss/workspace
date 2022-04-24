package com.icepoint.framework.workorder.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.icepoint.framework.data.dao.LongStdRepository;
import com.icepoint.framework.workorder.sys.entity.TaskSetting;

/**
 * 系统通知设置数据层
 * @author admin
 */
@Mapper
public interface TaskSettingRepository extends LongStdRepository<TaskSetting> {

}
