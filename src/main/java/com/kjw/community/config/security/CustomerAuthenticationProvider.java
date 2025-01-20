package com.kjw.community.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kjw.community.service.login.LoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerAuthenticationProvider implements AuthenticationProvider {

	private final PasswordEncoder bcryptoPasswordEncoder;

	private final LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
