package com.kjw.community.common.member;

public enum MemberActive {

	ACTIVE("Y"), DEACTIVATED("N");

	private String status;

	MemberActive(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

}
