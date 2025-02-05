package com.kjw.community.dto.common;

import java.io.Serializable;

import com.kjw.community.common.status.response.ResponseStatus;

public record PageResponseDto<T>(ResponseStatus status, T data, int totalSize, String msg) implements Serializable {

	private static final long serialVersionUID = 1L;

	public static <T> PageResponseDto<T> ofSuccess(T data, int totalSize, String msg) {
		return new PageResponseDto<T>(ResponseStatus.SUCCESS, data, totalSize, msg);
	}

	public static <T> PageResponseDto<T> ofError(T data, int totalSize, String msg) {
		return new PageResponseDto<T>(ResponseStatus.ERROR, data, totalSize, msg);
	}

}
