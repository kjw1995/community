package com.kjw.community.dto.session;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SessionDto {

	private Long memberIdx;

	private String id;

	private String nickname;

	private String phoneNumber;

	private String email;

	private Collection<? extends GrantedAuthority> roles;

}
