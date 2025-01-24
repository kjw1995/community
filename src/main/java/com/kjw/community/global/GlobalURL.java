package com.kjw.community.global;

public class GlobalURL {

	private static final String API_VERSION = "/api/v1";

	public static final String AUTH_URI = "/auth";
	public static final String MEMBER_URI = "/members";
	public static final String POST_URI = "/posts";
	public static final String ERROR_URI = "/error";

	public static final String VIEW_URI = "/views";
	public static final String VIEW_MAIN = VIEW_URI + "/main";
	public static final String VIEW_LOGIN = VIEW_URI + "/login";
	public static final String VIEW_SIGN = VIEW_URI + "/sign";
	public static final String VIEW_POST = VIEW_URI + POST_URI;
	public static final String VIEW_POST_DETAIL = VIEW_POST + "/detail";
	public static final String VIEW_POST_CREATE = VIEW_POST + "/create";
	public static final String VIEW_ERROR = VIEW_URI + ERROR_URI;

	public static final String LOGIN_URL = API_VERSION + AUTH_URI + MEMBER_URI + "/login";
	public static final String LOGOUT_URL = API_VERSION + AUTH_URI + MEMBER_URI + "/logout";
	public static final String MEMBER_URL = API_VERSION + MEMBER_URI;

}
