package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import entity.Shifts;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(description = "人员排班")
public class Schedule implements Comparable<Schedule> {

    private Long userId;
    private Long shiftsId;
    private String shiftsName;
    @ApiModelProperty(value = "工作日期")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date workDay;
    @ApiModelProperty(value = "班次")
    private List<Shifts> shifts;
    private String companyId;
    //批量新增
    private String shiftsIds;

    @Override
    public int compareTo(Schedule o) {
        return this.getWorkDay().compareTo(o.getWorkDay());
    }
}
