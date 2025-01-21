package com.kjw.community.common.exception;

public class UserRoleNotFoundException extends RuntimeException {

	private static final String DEFAULT_MESSAGE = "유저 권한을 찾을 수 업습니다.";

	public UserRoleNotFoundException(String message) {
		super(message);
	}

	public UserRoleNotFoundException() {
		super(DEFAULT_MESSAGE);
	}

}
