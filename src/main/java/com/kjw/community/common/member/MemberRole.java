package com.kjw.community.common.member;

public enum MemberRole {

	NORMAL("normal"), ADMIN("admin");

	MemberRole(String value) {
		this.value = value;
	}

	private String value;

	public String getValue() {
		return this.value;
	}

}
