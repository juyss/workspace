package com.icepoint.base.api.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DisposalMethodExcel extends BaseRowModel {

    @ExcelProperty(value = {"标题"}, index = 0)
    private String title;

    @ExcelProperty(value = {"发布人"}, index = 1)
    private String publisher;

    @ExcelProperty(value = {"发布时间"}, index = 2)
    private String releaseTime;

    @ExcelProperty(value = {"发布内容"}, index = 3)
    private String releaseContent;

    @ExcelProperty(value = {"门户搜索关键字"}, index = 4)
    private String keywords;

    @ExcelProperty(value = {"门户搜索描述"}, index = 5)
    private String description;

    @ExcelProperty(value = {"信息来源"}, index = 6)
    private String infoSources;

}
