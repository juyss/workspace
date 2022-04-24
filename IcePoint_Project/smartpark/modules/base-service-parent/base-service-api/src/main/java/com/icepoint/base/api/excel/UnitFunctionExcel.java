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
public class UnitFunctionExcel extends BaseRowModel {

    @ExcelProperty(value = {"切断污染源"}, index = 0)
    private String pollutionSources;

    @ExcelProperty(value = {"隔离污染区"}, index = 1)
    private String contaminatedZone;

    @ExcelProperty(value = {"防止污染扩散"}, index = 2)
    private String preventedProliferation;

    @ExcelProperty(value = {"减轻或消除污染物的危害"}, index = 3)
    private String easeHarm;

    @ExcelProperty(value = {"消除污染物及善后处理"}, index = 4)
    private String afterTreatment;

    @ExcelProperty(value = {"门户搜索关键字"}, index = 5)
    private String keywords;

    @ExcelProperty(value = {"门户搜索描述"}, index = 6)
    private String description;

    @ExcelProperty(value = {"信息来源"}, index = 7)
    private String infoSources;

}
