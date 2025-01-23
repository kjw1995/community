package com.kjw.community.common.message;

public enum ResponseMessage {

	SIGN_COMPLETE("회원가입 완료");

	final String value;

	ResponseMessage(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
