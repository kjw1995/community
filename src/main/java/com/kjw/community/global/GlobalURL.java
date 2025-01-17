package com.kjw.community.global;

public class GlobalURL {

	private static final String API_VERSION = "/api/v1";

	public static final String AUTH_URI = "/auth";
	public static final String MEMBER_URI = "/members";
	public static final String BOARD_URI = "/board";

	public static final String VIEW_URI = "/views";
	public static final String VIEW_MAIN = VIEW_URI + "/main";
	public static final String VIEW_LOGIN = VIEW_URI + "/login";
	public static final String VIEW_BOARD = VIEW_URI + "/board";

	public static final String LOGIN_URL = API_VERSION + AUTH_URI + MEMBER_URI + "/login";


}
