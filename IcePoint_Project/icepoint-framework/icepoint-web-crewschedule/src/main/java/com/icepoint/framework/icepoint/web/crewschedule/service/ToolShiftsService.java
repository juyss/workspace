package com.icepoint.framework.icepoint.web.crewschedule.service;

import com.icepoint.framework.icepoint.web.crewschedule.entity.ToolShifts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Juyss
 * @version 1.0
 * @ClassName ToolShiftsService
 * @description
 * @since 2021-07-27 15:37
 */
public interface ToolShiftsService {

    /**
     *
     * @author Juyss
     * @param toolShifts 数据实体
     * @return Integer
     */
    Integer insert(ToolShifts toolShifts);

    /**
     *
     * @author Juyss
     * @param toolShifts 数据实体
     * @return Integer
     */
    Integer update(ToolShifts toolShifts);

    /**
     *
     * @author Juyss
     * @param ids id集合
     * @return Integer
     */
    Integer delete(List<Long> ids);

    /**
     *
     * @author Juyss
     * @param toolShifts 条件
     * @param pageable 分页数据
     * @return Page<ToolShifts>
     */
    Page<ToolShifts> findAll(ToolShifts toolShifts, Pageable pageable);

    /**
     *
     * @author Juyss
     * @param id 主键
     * @return ToolShifts
     */
    ToolShifts findOne(Long id);
}
