package com.github.tangyi.common.core.model.req;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

/**
 * @author gaokx
 * @Description 新闻栏目推送req
 * @create 2021-03-25 11:15
 **/
@Data
@ToString
public class ParkNewsReq implements Serializable {

  private static final long serialVersionUID = 1L;
  /**
   * 栏目
   */
  @NotBlank(message = "新闻栏目不能为空")
  @ApiModelProperty(value = "新闻栏目")
  private String program;
  /**
   * 标题
   */
  @NotBlank(message = "新闻标题不能为空")
  @ApiModelProperty(value = "新闻标题")
  private String title;

  /**
   * 跳转url
   */
  @NotBlank(message = "跳转url不能为空")
  @ApiModelProperty(value = "跳转url")
  private String url;

}
