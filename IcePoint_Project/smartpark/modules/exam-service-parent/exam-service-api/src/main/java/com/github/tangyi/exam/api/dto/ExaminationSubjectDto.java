package com.github.tangyi.exam.api.dto;

import com.github.tangyi.exam.api.module.ExaminationSubject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gaokx
 * @Description
 * @create 2020-11-05 20:32
 **/
@Data
@NoArgsConstructor
public class ExaminationSubjectDto  extends ExaminationSubject {
  @ApiModelProperty(value = "题目名称")
  private String subjectName;

}
