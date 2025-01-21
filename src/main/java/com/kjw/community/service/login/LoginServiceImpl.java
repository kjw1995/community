package com.kjw.community.service.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kjw.community.common.exception.UserRoleNotFoundException;
import com.kjw.community.jpa.entity.Member;
import com.kjw.community.jpa.entity.Role;
import com.kjw.community.jpa.repository.member.MemberRepository;
import com.kjw.community.jpa.repository.role.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

	private final MemberRepository memberRepository;

	private final RoleRepository repository;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Member member = memberRepository.findByMemberId(username)
			.orElseThrow(() -> new UsernameNotFoundException("회원정보를 찾을 수 없습니다."));

		List<Role> roles = repository.findByMemberIdx(member.getId())
			.orElseThrow(UserRoleNotFoundException::new);

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getType()));
		}

		return User.builder()
			.username(member.getMemberId())
			.password(member.getPassword())
			.authorities(authorities)
			.build();
	}

}
