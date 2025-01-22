package com.kjw.community.dto.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CustomUserDetails implements UserDetails {

	private String id;

	private String nickname;

	private String email;

	private String phoneNumber;

	private List<SimpleGrantedAuthority> authorities;

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}
}
