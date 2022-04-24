package com.icepoint.framework.icepoint.web.crewschedule.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Excel导出实体类
 * @author Juyss
 * @version 1.0
 * @ClassName ScheduleToday
 * @description
 * @since 2021-07-30 15:13
 */
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleToday {

    @ExcelProperty("部门")
    private String deptName;

    @ExcelProperty("星期一")
    private String monday;

    @ExcelProperty("星期二")
    private String tuesday ;

    @ExcelProperty("星期三")
    private String wednesday ;

    @ExcelProperty("星期四")
    private String thursday ;

    @ExcelProperty("星期五")
    private String friday;

    @ExcelProperty("星期六")
    private String saturday;

    @ExcelProperty("星期日")
    private String sunday;
}
