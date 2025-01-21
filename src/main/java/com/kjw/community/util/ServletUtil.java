package com.kjw.community.util;

import jakarta.servlet.http.HttpServletRequest;

public class ServletUtil {

	private static final String XHR_HEADER = "XMLHttpRequest";
	private static final String XHR_REQUEST_HEADER = "X-Requested-with";

	public static boolean isAjaxRequest(HttpServletRequest request) {
		return XHR_HEADER.equalsIgnoreCase(request.getHeader(XHR_REQUEST_HEADER));
	}

}
