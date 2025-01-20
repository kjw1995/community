package com.kjw.community.service.sign;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.kjw.community.common.member.MemberActive;
import com.kjw.community.common.member.MemberRole;
import com.kjw.community.dto.common.ResponseDto;
import com.kjw.community.dto.sign.SignUpRequestDto;
import com.kjw.community.jpa.entity.Member;
import com.kjw.community.jpa.entity.Role;
import com.kjw.community.jpa.repository.member.MemberRepository;
import com.kjw.community.jpa.repository.role.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignServiceImpl implements SignService {

	private final MemberRepository memberRepository;

	private final RoleRepository roleRepository;

	@Override
	public ResponseEntity<ResponseDto<Void>> signup(SignUpRequestDto requestDto) {

		Member member = Member.builder()
			.memberId(requestDto.id())
			.password(requestDto.password())
			.phonenumber(requestDto.phoneNumber())
			.email(requestDto.email())
			.isActive(MemberActive.ACTIVE.getStatus())
			.createdAt(LocalDateTime.now())
			.build();

		Member result = memberRepository.save(member);

		Role role = Role.builder()
			.memberIdx(result.getId())
			.type(MemberRole.NORMAL.getValue())
			.createdAt(LocalDateTime.now())
			.build();

		roleRepository.save(role);

		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.ofSuccessWithMsg("회원가입 성공"));
	}

}
