package bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("值班表bean")
public class ScheduleTableListBean {

    @ApiModelProperty("工作日期")
    private Date workDay;

    @ApiModelProperty("名称")
    private String name; //

    @ApiModelProperty("部门ID")
    private Long deptId;

    @ApiModelProperty("部门名称")
    private String deptName;


}
