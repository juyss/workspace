package com.github.tangyi.exam.api.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author gaokx
 * @Description 未加入到当前考试的试题查询
 * @create 2020-11-14 22:45
 **/
@Data
public class NotAddExaminationSubjectQry {

  @ApiModelProperty(value = "需排除的考试id")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long excludeExaminationId;

  @ApiModelProperty(value = "题目分类ID")
  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Long categoryId;

  @ApiModelProperty(value = "题目ID")
  private String subjectId;

  @ApiModelProperty(value = "题目名称")
  private String subjectName;

  @ApiModelProperty(value = "题目类型")
  private Integer type;

}
