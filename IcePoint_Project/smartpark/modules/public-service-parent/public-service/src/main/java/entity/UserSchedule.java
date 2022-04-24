package entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel( description = "人员排班信息")
public class UserSchedule  {


    private Long userId;

    private String userName;
    private Long deptId;

    private String deptName;
    private String roleIds;

    private String roleNames;

    List<Schedule> schedules;

    private Long userScheduleId;



}
