package com.github.tangyi.exam.enums;

import com.github.tangyi.exam.service.ISubjectService;
import com.github.tangyi.exam.service.SubjectChoicesService;
import com.github.tangyi.exam.service.SubjectCommonService;
import com.github.tangyi.exam.service.SubjectJudgementService;
import com.github.tangyi.exam.service.SubjectShortAnswerService;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 题目类型枚举
 *
 * @author tangyi
 * @date 2019/6/16 16:22
 */
@Getter
@AllArgsConstructor
public enum SubjectTypeEnum {

	CHOICES("单选题", 0, SubjectCommonService.class),

	SHORT_ANSWER("简答题", 1, SubjectCommonService.class),

	JUDGEMENT("判断题", 2, SubjectCommonService.class),

	MULTIPLE_CHOICES("多选题", 3, SubjectCommonService.class);

	private String name;

	private Integer value;

	private Class<? extends ISubjectService> service;

	/**
	 * 根据类型返回具体的SubjectType
	 *
	 * @param value value
	 * @return SubjectType
	 */
	public static SubjectTypeEnum matchByValue(Integer value) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.value.equals(value)) {
				return item;
			}
		}
		return CHOICES;
	}

	/**
	 * 根据描述返回具体的SubjectType
	 *
	 * @param name name
	 * @return SubjectType
	 */
	public static SubjectTypeEnum matchByName(String name) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.name.equals(name)) {
				return item;
			}
		}
		return CHOICES;
	}


	/**
	 * 是否选择题
	 *
	 * @param  value
	 * @return SubjectType
	 */
	public static  boolean isChoices(int value) {
		for (SubjectTypeEnum item : SubjectTypeEnum.values()) {
			if (item.value == value) {
			 return  true ;
			}
		}
		return false;
	}

}
