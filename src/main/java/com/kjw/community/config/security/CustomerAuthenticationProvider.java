package com.kjw.community.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kjw.community.dto.security.CustomUserDetails;
import com.kjw.community.service.login.LoginService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class CustomerAuthenticationProvider implements AuthenticationProvider {

	private final PasswordEncoder bcryptoPasswordEncoder;

	private final LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		CustomUserDetails userDetails = (CustomUserDetails)loginService.loadUserByUsername(
			authentication.getPrincipal().toString());

		log.info("credentials = " + authentication.getCredentials().toString());

		if (!bcryptoPasswordEncoder.matches(userDetails.getPassword(), authentication.getCredentials().toString())) {
			throw new UsernameNotFoundException("비밀번호를 확인해주세요.");
		}

		UsernamePasswordAuthenticationToken userAuthenticationToken = UsernamePasswordAuthenticationToken.authenticated(
			userDetails.getUsername(), null,
			userDetails.getAuthorities());
		userAuthenticationToken.setDetails(userDetails);

		return userAuthenticationToken;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
