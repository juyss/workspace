package com.github.tangyi.exam.api.module;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.tangyi.common.core.persistence.BaseEntity;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 题
 *
 * @author gaokx
 * @date 2020/11/8 20:53
 */
@Data
@NoArgsConstructor
public class Subject extends BaseEntity<Subject> {

	/**
	 * 题目分类ID
	 */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Long categoryId;

	/**
	 * 题目名称
	 */
	@NotBlank(message = "题目名称不能为空")
	private String subjectName;

	/**
	 * 参考答案
	 */
	private String answer;

	/**
	 * 分值
	 */
	@NotBlank(message = "题目分值不能为空")
	private Double score;

	/**
	 * 解析
	 */
	private String analysis;

	/**
	 * 难度等级
	 */
	private Integer level;

	/**
	 * 题目类型，0：单选题 1：简答题 2： 判断题 3：多选题
	 */

	private Integer type ;

	/**
	 * 选项列表
	 */
	private List<SubjectOption> options;

	/**
	 * 分类id列表
	 */
  private List<Long> categoryIdList;

}
