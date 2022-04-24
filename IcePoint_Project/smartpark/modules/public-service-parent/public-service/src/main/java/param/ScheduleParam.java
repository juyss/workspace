package param;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "人员排班参数")
public class ScheduleParam {

    @ApiModelProperty(value = "人员名称")
    private String userName;
    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private List<Long> deptIds;

    private Long roleId;

    /**
     * 排班日期
     */
    @ApiModelProperty(value = "排班日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workDate;

    /**
     * 排班开始日期
     */
    @ApiModelProperty(value = "排班开始日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date startDate;

    /**
     * 排班结束日期
     */
    @ApiModelProperty(value = "排班结束日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;

    /**
     * 排班一周时间
     */
    @ApiModelProperty(value = "排班一周时间")
    private List<String> week;

    @ApiModelProperty(value = "页码")
    private Integer page = 1;

    @ApiModelProperty(value = "分页大小")
    private Integer rows = 10;

}
