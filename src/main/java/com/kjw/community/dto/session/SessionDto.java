package com.kjw.community.dto.session;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class SessionDto {

	private String id;

	private String nickname;

	private String phoneNumber;

	private String email;

	private List<String> roles;

}
