package com.kjw.community.dto.common;

import java.io.Serializable;

import com.kjw.community.common.status.response.ResponseStatus;

public record ResponseDto<T>(ResponseStatus status, T data, String msg) implements Serializable {

	private static final long serialVersionUID = 1L;

	public static <T> ResponseDto<T> ofSuccess(T data, String msg) {
		return new ResponseDto<T>(ResponseStatus.SUCCESS, data, msg);
	}

	public static ResponseDto<Void> ofSuccessWithMsg(String msg) {
		return new ResponseDto<Void>(ResponseStatus.SUCCESS, null, msg);
	}

	public static <T> ResponseDto<T> ofError(T data, String msg) {
		return new ResponseDto<T>(ResponseStatus.ERROR, data, msg);
	}

	public static ResponseDto<Void> ofErrorWithMsg(String msg) {
		return new ResponseDto<Void>(ResponseStatus.ERROR, null, msg);
	}

}
