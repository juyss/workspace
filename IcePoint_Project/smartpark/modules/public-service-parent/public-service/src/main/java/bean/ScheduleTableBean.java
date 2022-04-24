package bean;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;
import java.util.Map;

@Data
@ApiModel("值班表bean")
public class ScheduleTableBean {

    @ApiModelProperty("值班数据字符串")
    @ExcelProperty(value = "值班数据", index =1)
    private String daysStr; // {userId, workDay, username}|{...}...

    @ApiModelProperty("值班天数数据")
    @ExcelProperty(value = "值班天数数据", index =2)
    private Map<String, Collection<ScheduleWorkerBean>> days; // [workday, worker]

    @ApiModelProperty("区域路段编码")
    private Long deptId;

    @ApiModelProperty("区域路段名称")
    @ExcelProperty(value = "部门名称", index =3)
    private String deptName;


}
