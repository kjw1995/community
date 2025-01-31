package com.kjw.community.common.handler.login;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.kjw.community.dto.security.CustomUserDetails;
import com.kjw.community.dto.session.SessionDto;
import com.kjw.community.global.GlobalURL;
import com.kjw.community.util.SessionUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private final SessionUtil sessionUtil;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {

		try {
			CustomUserDetails userDetails = (CustomUserDetails)authentication.getDetails();

			sessionUtil.setUserSession(
				SessionDto.builder()
					.memberIdx(userDetails.getMemberIdx())
					.id(authentication.getPrincipal().toString())
					.email(userDetails.getEmail())
					.nickname(userDetails.getNickname())
					.phoneNumber(userDetails.getPhoneNumber())
					.roles(userDetails.getAuthorities())
					.build()
			);

			response.sendRedirect(GlobalURL.VIEW_MAIN);

		} catch (Exception e) {
			log.error("onAuthenticationSuccess error", e);
			response.sendRedirect(GlobalURL.VIEW_ERROR);
		}

	}

}
