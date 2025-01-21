package com.kjw.community.common.handler.login;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.global.GlobalURL;
import com.kjw.community.util.ServletUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginFailureHandler implements AuthenticationFailureHandler {

	private final ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {

		log.info("로그인 실패", exception);
		if (ServletUtil.isAjaxRequest(request)) {
			response.setContentType(MediaType.APPLICATION_JSON_VALUE);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write(objectMapper.writeValueAsString(ResponseDto.ofErrorWithMsg("로그인 중 문제 발생")));
		} else {
			response.sendRedirect(GlobalURL.VIEW_ERROR);
		}

	}

}
