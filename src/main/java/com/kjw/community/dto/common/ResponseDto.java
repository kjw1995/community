package com.kjw.community.dto.common;

public record ResponseDto<T>(T data, String msg) {

	public static <T> ResponseDto ofSuccess(T data, String msg) {
		return new ResponseDto(data, msg);
	}

	public static <T> ResponseDto ofSuccessWithMsg(String msg) {
		return new ResponseDto(null, msg);
	}

}
