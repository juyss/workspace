package com.github.tangyi.common.core.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * 服务异常
 *
 * @author tangyi
 * @date 2019-10-08 12:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends CommonException {

	private static final long serialVersionUID = -7285211528095468156L;

	private Integer code;

	public ServiceException() {
	}

	public ServiceException(String msg) {
		super(msg);
	}

	public ServiceException(Integer code) {
		this.code = code;
	}
}
