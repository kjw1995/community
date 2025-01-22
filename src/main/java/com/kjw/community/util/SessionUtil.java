package com.kjw.community.util;

import org.springframework.stereotype.Component;

import com.kjw.community.dto.session.SessionDto;

@Component
public class SessionUtil {

	private final String USER_SESSION_KEY = "SESSION_USER";

	public SessionDto getUserSession() {
		return (SessionDto)getAttribute(USER_SESSION_KEY);
	}

	public void setUserSession(SessionDto sessionDto) {
		setAttribute(USER_SESSION_KEY, sessionDto);
	}

	private Object getAttribute(String key) {
		return ServletUtil.getSession().getAttribute(key);
	}

	private void setAttribute(String key, Object value) {
		ServletUtil.getSession().setAttribute(key, value);
	}

	private void removeAttribute(String key) {
		ServletUtil.getSession().removeAttribute(key);
	}

	public void removeAttributeUser() {
		removeAttribute(USER_SESSION_KEY);
	}

}
