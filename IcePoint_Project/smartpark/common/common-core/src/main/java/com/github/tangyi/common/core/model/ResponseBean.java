package com.github.tangyi.common.core.model;

import com.github.tangyi.common.core.constant.ApiMsg;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回数据
 *
 * @author tangyi
 * @date 2019/3/17 12:08
 */
@Data
public class ResponseBean<T> implements Serializable {

	public static final long serialVersionUID = 42L;

	private String msg = ApiMsg.msg(ApiMsg.KEY_SUCCESS);

	private int code = ApiMsg.KEY_SUCCESS;

	private T data;

	public ResponseBean() {
		super();
	}

	public ResponseBean(T data) {
		super();
		this.data = data;
	}

	public ResponseBean(T data, int keyCode, int msgCode) {
		super();
		this.data = data;
		this.code = Integer.parseInt(keyCode + "" + msgCode);
		this.msg = ApiMsg.code2Msg(keyCode, msgCode);
	}

	public ResponseBean(T data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}

	public ResponseBean(T data, int code) {
		super();
		this.data = data;
		this.code = code;
		this.msg = ApiMsg.msg(code);
	}

	public ResponseBean(T data, int code, String msg) {
		super();
		this.data = data;
		this.code = code;
		this.msg = msg;
	}

	public static Builder success() {
		return new Builder(ApiMsg.KEY_SUCCESS, null);
	}

	public static <T> ResponseBean<T> success(T data) {
		return success().data(data);
	}

	public static Builder error() {
		return new Builder(ApiMsg.ERROR, null);
	}

	public static Builder empty() {
		return new Builder(ApiMsg.EMPTY, null);
	}

	public static Builder notFound() {
		return new Builder(ApiMsg.NOT_FOUND, null);
	}

	public static Builder fail() {
		return new Builder(ApiMsg.FAILED, null);
	}

	@AllArgsConstructor
	public static class Builder {

		private int code;

		private String msg;

		public <T> ResponseBean<T> build() {
			return data(null);
		}

		public <T> ResponseBean<T> data(T data) {
			return msg != null ? new ResponseBean<>(data, code, msg)
					: new ResponseBean<>(data ,code);
		}

		public Builder msg(String msg) {
			this.msg = msg;
			return this;
		}

		public Builder code(int code) {
			this.code = code;
			return this;
		}
	}
}
