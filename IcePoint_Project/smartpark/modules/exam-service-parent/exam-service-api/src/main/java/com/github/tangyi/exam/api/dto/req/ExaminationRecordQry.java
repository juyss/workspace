package com.github.tangyi.exam.api.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
 * @author gaokx
 * @Description
 * @create 2020-11-05 21:05
 **/
@Data
public class ExaminationRecordQry {
  @ApiModelProperty(value = "userId")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long userId;

  @ApiModelProperty(value = "考试ID")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long examinationId;

  @ApiModelProperty(value = "考试类型")
  private Integer examinationType;

  @ApiModelProperty(value = "用户姓名")
  private String userName;
  @ApiModelProperty(value = "手机号")
  private String phone;
  @ApiModelProperty(value = "考试名称")
  private String examinationName;

  @ApiModelProperty(value = "开始时间")
  private Date startTime;
  @ApiModelProperty(value = "结束时间")
  private Date endTime;
  @ApiModelProperty(value = "提交状态")
  private Integer submitStatus;
  @ApiModelProperty(name = "系统编号")
  protected String applicationCode;
  @ApiModelProperty(name = "租户编号")
  protected String tenantCode;

}
