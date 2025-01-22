package com.kjw.community.util;

import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.thymeleaf.util.ObjectUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ServletUtil {

	private static final String XHR_HEADER = "XMLHttpRequest";
	private static final String XHR_REQUEST_HEADER = "X-Requested-with";

	public static boolean isAjaxRequest(HttpServletRequest request) {
		return XHR_HEADER.equalsIgnoreCase(request.getHeader(XHR_REQUEST_HEADER));
	}

	public static HttpServletRequest getRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		return ObjectUtils.nullSafe(requestAttributes.getRequest(), null);
	}

	public static HttpServletResponse getResponse() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		Assert.notNull(requestAttributes, "Request is null");
		return ObjectUtils.nullSafe(requestAttributes.getResponse(), null);
	}

	public static HttpSession getSession() {
		return getRequest().getSession();
	}

}
