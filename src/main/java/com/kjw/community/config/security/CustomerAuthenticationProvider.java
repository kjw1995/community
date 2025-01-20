package com.kjw.community.config.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kjw.community.service.login.LoginService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerAuthenticationProvider implements AuthenticationProvider {

	private final PasswordEncoder bcryptoPasswordEncoder;

	private final LoginService loginService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		UserDetails userDetails = loginService.loadUserByUsername(authentication.getPrincipal().toString());

		if (!bcryptoPasswordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
			throw new UsernameNotFoundException("회원 정보를 찾을 수 없습니다.");
		}

		return UsernamePasswordAuthenticationToken.authenticated(userDetails.getUsername(), userDetails.getPassword(),
			userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
